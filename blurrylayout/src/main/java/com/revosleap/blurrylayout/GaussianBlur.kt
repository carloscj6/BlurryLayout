package com.revosleap.blurrylayout

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


internal object GaussianBlur {
    private var blurry: Bitmap? = null


    fun blurred(context: Context, bitmap: Bitmap, radius: Int): Bitmap {
        var img = bitmap
        try {
            img = colorEncodingChange(img)
            blurry = Bitmap.createBitmap(img.width, img.height,
                    Bitmap.Config.ARGB_8888)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val renderScript = RenderScript.create(context)
        val blurInput = Allocation.createFromBitmap(renderScript, img)
        val blurOutput = Allocation.createFromBitmap(renderScript, blurry)
        val blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript))
        blur.setInput(blurInput)
        blur.setRadius(radius.toFloat())
        blur.forEach(blurOutput)
        blurOutput.copyTo(img)
        renderScript.destroy()
        return img
    }

    @Throws(Exception::class)
    private fun colorEncodingChange(img: Bitmap): Bitmap {
        val pixelNum = img.width * img.height
        val pixels = IntArray(pixelNum)
        img.getPixels(pixels, 0, img.width, 0, 0, img.width, img.height)
        val result = Bitmap.createBitmap(img.width, img.height, Bitmap.Config.ARGB_8888)
        result.setPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        return result
    }

}
