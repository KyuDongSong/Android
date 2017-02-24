### Beacon info
<pre>
UUID : 20CAE8A0-A9CF-11E3-A5E2-0800200C9A66
Major : 82
Minor : 60872
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
  compile 'com.estimote:sdk:0.10.4@aar'
}
</pre>

### MyApplication extends Application
> Applicaiton은 Android Applicaition의 공통 모듈이라고 생각하면 됩니다.

<pre>

</pre>
