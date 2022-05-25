package com.example.facedetectionmakeup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.facedetectionmakeup.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    val Perm_camera = arrayOf(Manifest.permission.CAMERA)
    val Perm_storage = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    val Req_storage = 99
    val Req_camera = 100
    val Take_camera = 101

    val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*앱이 시작되면 스토리지 권한을 처리*/
        requirePermissions(Perm_storage, Req_storage)
        /*카메라 버튼이 클릭되면 권한처리 후 카메라를 오픈한다.*/
        binding.btnCamera.setOnClickListener{
            requirePermissions(Perm_camera, Req_camera)
        }
    }

    fun setView() {

    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode) {
            Req_storage -> {
                Toast.makeText(this, "스토리지 권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
            }
            Req_camera -> {
                openCamera()
            }
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode) {
            Req_storage -> {
                Toast.makeText(this, "스토리지 권한이 없으면 앱을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            Req_camera -> {
                Toast.makeText(this, "카메라 권한이 거절되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Take_camera)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when(requestCode) {
                Take_camera -> {
                    /*카메라 촬영 결과를 처리*/
                    val bitmap = data?.extras?.get("data") as Bitmap /*미리보기 이미지*/
                }
            }
        }
    }
}