package com.revosleap.blurlayout

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

import com.revosleap.blurrylayout.BlurryLayout


class Blurry : AppCompatActivity() {
    private var blurLayout: BlurryLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_blurry)
        blurLayout = findViewById(R.id.blurLayout)
        val image = BitmapFactory.decodeResource(resources, R.drawable.medium_cover)
        blurLayout!!.setBitmapBlurry(image, 10, 10)

    }
}
