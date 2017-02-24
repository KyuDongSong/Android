### Runtime Permission Check
<pre>
private void checkSMSReceivedPermission() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        /*
         * 해당 App이 특정 권한을 가지고 있는지 검사함.
         * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
         * PackageManager.PERMISSION_DENIED : 권한이 없음
         * PackageManager.PERMISSION_GRANTED : 권한이 있음.
         */
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        // 요청한 권한이 없을 경우
        if (permissionCheck == PERMISSION_DENIED) {

            /*
             * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
             * Marshmellow 버젼 이상부터 사용가능함.
             */
            boolean isPermissionDeny = this.shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS);
            if (isPermissionDeny) {
                // 사용자가 권한을 Deny 한 경우.
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("권한 요청")
                        .setMessage("이 Application은 사용자의 SMS로 단어장을 추가하는 앱입니다. 계속하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    MainActivity.this.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 10001);
                                }
                            }
                        })
                        .create().show();
            }
            else {
                // 처음 권한을 요청 할 경우.
                this.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 10001);
            }

        }
    }

}
</pre>
