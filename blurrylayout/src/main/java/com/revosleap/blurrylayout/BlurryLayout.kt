package com.revosleap.blurrylayout

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import kotlin.NullPointerException

/**
 * @property BlurryLayout custom layout that extends FrameLayout
 */
class BlurryLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
    private val linearLayout: LinearLayout
    private val imageView: ImageView
    private val imageDrawable: Drawable?
    private var DEFAULT_IMAGE: Drawable? = null

    init {
        View.inflate(context, R.layout.blurry_layout, this)
        linearLayout = findViewById(R.id.linearLayout)
        imageView = findViewById(R.id.imageView)
        DEFAULT_IMAGE = ContextCompat.getDrawable(context, R.drawable.image)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlurryLayout, defStyleAttr, 0)
        BLUR_COLOR = typedArray.getColor(R.styleable.BlurryLayout_blurColor, DEFAULT_BLUR_COLOR)
        ALPHA = typedArray.getFloat(R.styleable.BlurryLayout_blurOpacity, DEFAULT_ALPHA)
        BLUR_RADIUS = typedArray.getInt(R.styleable.BlurryLayout_blurRadius, DEFAULT_BLUR_RADIUS)
        imageDrawable = typedArray.getDrawable(R.styleable.BlurryLayout_blurImage)
        typedArray.recycle()
        setImageBg()
    }


    private fun setImageBg() {
        val bg: Drawable?
        if (imageDrawable != null) {
            //To get a good effect from user image
            setDrawableBlur(imageDrawable)
        } else {
            bg = DEFAULT_IMAGE
            val image = (bg as? BitmapDrawable)?.bitmap
            try {
                imageView.setImageBitmap(GaussianBlur.blurred(context, image!!, BLUR_RADIUS))
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }

        linearLayout.setBackgroundColor(BLUR_COLOR)
        linearLayout.alpha = ALPHA

    }

    /**
     * @param blurColor - the color atop the blurred layout.
     */
    fun blurColor(blurColor: Int) {
        linearLayout.setBackgroundColor(blurColor)
    }

    /**
     * @param bluropacity - the opacity of the blurred layout
     */
    fun blurOpacity(bluropacity: Float) {
        linearLayout.alpha = bluropacity
    }

    /**
     * set background image for the blurred background from drawable resource
     */
    fun setDrawableBlur(imageDrawable: Drawable, radius: Int, blurPercentage: Int) {
        val image = (imageDrawable as BitmapDrawable).bitmap
        val height = image.height
        val width = image.width
        val background = ThumbnailUtils.extractThumbnail(image, width * blurPercentage / 100, height * blurPercentage / 100)
        imageView.setImageBitmap(GaussianBlur.blurred(context, background, radius))
    }

    /**
     * set background image for the blurred background from drawable resource
     */
    fun setDrawableBlur(imageDrawable: Drawable, radius: Int) {
        setDrawableBlur(imageDrawable, radius, 10)
    }

    /**
     * set background image for the blurred background from drawable resource
     */
    fun setDrawableBlur(imageDrawable: Drawable) {
        setDrawableBlur(imageDrawable, 10, 10)
    }

    /**
     * set background image for the blurred background from bitmap image
     */
    fun setBitmapBlur(bitmapBlur: Bitmap, radius: Int, blurPercentage: Int) {
        val height = bitmapBlur.height
        val width = bitmapBlur.width
        val bg = ThumbnailUtils.extractThumbnail(bitmapBlur, width * blurPercentage / 100, height * blurPercentage / 100)
        imageView.setImageBitmap(GaussianBlur.blurred(context, bg, radius))
    }

    /**
     * set background image for the blurred background from bitmap image
     */
    fun setBitmapBlur(bitmapBlur: Bitmap, radius: Int) {
        setBitmapBlur(bitmapBlur, radius, 10)
    }

    /**
     * set background image for the blurred background from bitmap image
     */
    fun setBitmapBlur(bitmapBlur: Bitmap) {
        setBitmapBlur(bitmapBlur, 10, 10)
    }

    companion object {
        private val DEFAULT_BLUR_COLOR = Color.WHITE
        private var BLUR_COLOR: Int = 0
        private val DEFAULT_BLUR_RADIUS = 10
        private var BLUR_RADIUS: Int = 10
        private val DEFAULT_ALPHA = 0.3f
        private var ALPHA: Float = 10F
    }
}
