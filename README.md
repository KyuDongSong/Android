# Permission
<pre>
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*
             * 해당 App이 특정 권한을 가지고 있는지 검사함.
             * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
             * PackageManager.PERMISSION_DENIED : 권한이 없음
             * PackageManager.PERMISSION_GRANTED : 권한이 있음.
             */
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            /*
             * 해당 권한이 없을 경우 처리 방법
             */
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {

                /*
                 * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
                 * 여기서 true가 나올 경우는 해당 앱에서 한번이라도 권한을 Deny한 경우일 때 말고는 없음.
                 * 권한에 대해서 허가하지 않은 경우 다시 한번 권한의 취득을 위해 사용자에게 이유를 고지해야 함.
                 * Marshmellow 버젼 이상부터 사용가능함.
                 */
                if (this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                    /*
                     * 권한 취득해야 하는 이유를 Dialog 등을 통해서 알린다.
                     */
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("권한 요청")
                            .setMessage("주소록 접근 권한이 필요합니다.")
                            .setPositiveButton("허용", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    /*
                                     * 권한의 취득을 요청한다.
                                     * 취득하고자 하는 권한을 배열에 넣고 요청한다.
                                     * 뒤에 들어가는 파라미터(requestCode)는 onRequestPermissionsResult() 에서 권한 취득 결과에서 사용된다.
                                     * startActiviryForResult의 Request Code와 유사함.
                                     */
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        MainActivity.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
                                    }
                                }
                            })
                            .setNegativeButton("거부", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();

                } else {
                    /*
                     * 권한의 취득 요청을 처음 할 때
                     * 권한의 취득을 요청한다.
                     * 취득하고자 하는 권한을 배열에 넣고 요청한다.
                     * 뒤에 들어가는 파라미터(1000)는 onRequestPermissionsResult() 에서 권한 취득 결과에서 사용된다.
                     * startActiviryForResult의 Request Code와 유사함.
                     */
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
                }

            }
        }
</pre>
