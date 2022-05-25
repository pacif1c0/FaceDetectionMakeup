package com.example.facedetectionmakeup

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.security.Permissions
import android.content.pm.PackageManager

abstract class BaseActivity : AppCompatActivity() {

    abstract fun permissionGranted(requestCode: Int)
    abstract fun permissionDenied(requestCode: Int)

    /*권한 검사*/
    fun requirePermissions(permissions:Array<String>, requestCode:Int) {
        /*API버전이 마시멜로 미만이면 권한처리가 필요없다*/
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        } else {
            /*권한이 없으면 권한 요청 -> 팝업*/
            ActivityCompat.requestPermissions(this, permissions, requestCode)
        }

    }
    /*결과처리*/
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if ( grantResults.all { it == PackageManager.PERMISSION_GRANTED } ) {
            permissionGranted(requestCode)
        } else {
            permissionDenied(requestCode)
        }
    }
}