package com.revosleap.blurrylayout

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout


class BlurryLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
    private val linearLayout: LinearLayout
    private val imageView: ImageView
    private val imageDrawable: Drawable?
    private var DEFAULT_IMAGE: Drawable

    init {
        View.inflate(context, R.layout.blurry_layout, this)
        linearLayout = findViewById(R.id.linearLayout)
        imageView = findViewById(R.id.imageView)
        DEFAULT_IMAGE = resources.getDrawable(R.drawable.image)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlurryLayout, defStyleAttr, 0)
        BLUR_COLOR = typedArray.getColor(R.styleable.BlurryLayout_blurColor, DEFAULT_BLUR_COLOR)
        ALPHA = typedArray.getFloat(R.styleable.BlurryLayout_blurOpacity, DEFAULT_ALPHA)
        BLUR_RADIUS = typedArray.getInt(R.styleable.BlurryLayout_blurRadius, DEFAULT_BLUR_RADIUS)
        imageDrawable = typedArray.getDrawable(R.styleable.BlurryLayout_blurImage)
        typedArray.recycle()
        setImageBg()
    }

    // Set linear layout properties
    private fun setImageBg() {
        val bg: Drawable
        if (imageDrawable != null) {
            //To get a good effect from user image
            blurBackground(imageDrawable, BLUR_RADIUS)
        } else {
            bg = DEFAULT_IMAGE
            val image = (bg as BitmapDrawable).bitmap
            imageView.setImageBitmap(GaussianBlur.blurred(context, image, BLUR_RADIUS))
        }

        linearLayout.setBackgroundColor(BLUR_COLOR)
        linearLayout.alpha = ALPHA

    }

    //Its good to allow programatic setting
    fun blurColor(blurColor: Int) {
        linearLayout.setBackgroundColor(blurColor)
    }

    fun blurOpacity(bluropacity: Float) {
        linearLayout.alpha = bluropacity
    }

    //Allow programmatic setting of image and blur radius
    @Deprecated("")
    fun blurBackground(imageDr: Drawable, radius: Int) {
        val image = (imageDr as BitmapDrawable).bitmap
        val height = image.height
        val width = image.width
        val background = ThumbnailUtils.extractThumbnail(image, width * 3 / 100, height * 3 / 100)
        imageView.setImageBitmap(GaussianBlur.blurred(context, background, radius))
    }

    fun setDrawableBlur(imageDr: Drawable, radius: Int) {
        val image = (imageDr as BitmapDrawable).bitmap
        val height = image.height
        val width = image.width
        val background = ThumbnailUtils.extractThumbnail(image, width, height)
        imageView.setImageBitmap(GaussianBlur.blurred(context, background, radius))
    }

    fun setDrawableBlurry(imageDr: Drawable, radius: Int, blurPercentage: Int) {
        val image = (imageDr as BitmapDrawable).bitmap
        val height = image.height
        val width = image.width
        val background = ThumbnailUtils.extractThumbnail(image, width * blurPercentage / 100, height * blurPercentage / 100)
        imageView.setImageBitmap(GaussianBlur.blurred(context, background, radius))
    }


    fun setBitmapBlur(bitmapBlur: Bitmap, radius: Int) {
        val height = bitmapBlur.height
        val width = bitmapBlur.width
        val bg = ThumbnailUtils.extractThumbnail(bitmapBlur, width, height)
        imageView.setImageBitmap(GaussianBlur.blurred(context, bg, radius))
    }

    fun setBitmapBlurry(bitmapBlur: Bitmap, radius: Int, blurPercentage: Int) {
        val height = bitmapBlur.height
        val width = bitmapBlur.width
        val bg = ThumbnailUtils.extractThumbnail(bitmapBlur, width * blurPercentage / 100, height * blurPercentage / 100)
        imageView.setImageBitmap(GaussianBlur.blurred(context, bg, radius))
    }

    companion object {
        private val DEFAULT_BLUR_COLOR = Color.WHITE
        private var BLUR_COLOR: Int=0
        private val DEFAULT_BLUR_RADIUS = 10
        private var BLUR_RADIUS: Int=10
        private val DEFAULT_ALPHA = 0.3f
        private var ALPHA: Float=10F
    }
}
