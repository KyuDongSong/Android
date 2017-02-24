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
  jcenter()
}

dependencies {
  ...
  // 추가 할 부분
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
