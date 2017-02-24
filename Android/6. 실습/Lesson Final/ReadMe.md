### Beacon info
<pre>
UUID : 74278BDA-B644-4520-8F0C-720EAF059935
Major : 0
Minor : 0
</pre>

### build.gradle (Module:App)
<pre>
// 추가 할 부분
repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  ...
  // 추가 할 부분
  compile 'com.facebook.android:facebook-android-sdk:4.+'
  compile 'com.estimote:sdk:0.12.0@aar'
}
</pre>

### MyApplication extends Application
> Applicaiton은 Android Applicaition의 공통 모듈이라고 생각하면 됩니다.

<pre>
private BeaconManager beaconManager;

@Override
public void onCreate() {
    super.onCreate();
    beaconManager = new BeaconManager(getApplicationContext());

    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
        @Override
        public void onServiceReady() {
            beaconManager.startMonitoring(
                    new Region("monitored region",
                            UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), 0, 0));
        }
    });

    beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
        @Override
        public void onEnteredRegion(Region region, List&lt;Beacon&gt; list) {
            //TODO 액티비티를 띄우는 처리

        }

        @Override
        public void onExitedRegion(Region region) {

        }
    });
}
</pre>



### MainActivity.java
<pre>
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    /*** Beacon ***/
    private BeaconManager beaconManager;
    private Region region;
    /*** Beacon ***/


    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        /*** Beacon ***/
//        beaconManager = new BeaconManager(this);
//        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
//            @Override
//            public void onBeaconsDiscovered(Region region, List&lt;Beacon&gt; list) {
//                if ( !list.isEmpty() ) {
//                    Beacon nearestBeacon = list.get(0);
//                    Log.d("BEAC", "onBeaconsDiscovered: " + nearestBeacon.getRssi());
//                }
//            }
//        });
//
//        region = new Region("Ranged region", UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), 0, 0);
        /*** Beacon ***/

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.btn_facebook_login);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        SetxString&gt; permissions = AccessToken.getCurrentAccessToken().getPermissions();
        List&lt;Object&gt; permissionList = Arrays.asList(permissions.toArray());

        if(!permissionList.contains("publish_actions")) {
            LoginManager.getInstance().logInWithPublishPermissions(MainActivity.this, Arrays.asList("publish_actions"));
        }

        loginButton.registerCallback(callbackManager, new FacebookCallback&lt;LoginResult&gt;() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("result",object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });

        Bundle params = new Bundle();
        params.putString("message", "aslkjdsfljksalfjsd111");
        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("result", response.toString());
                    }
                }
        ).executeAsync();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*** Beacon ***/
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManager.startRanging(region);
//            }
//        });
        /*** Beacon ***/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
</pre>
