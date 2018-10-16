package com.revosleap.blurrylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;




public class BlurryLayout extends FrameLayout {
    private LinearLayout linearLayout;
    private ImageView imageView;
    private static int DEFAULT_BLUR_COLOR=Color.WHITE;
    private static int BLUR_COLOR;
    private static int DEFAULT_BLUR_RADIUS=10;
    private static int BLUR_RADIUS;
    private static float DEFAULT_ALPHA=0.3f;
    private static float ALPHA;
    private Drawable imageDrawable;
    Drawable DEFAULT_IMAGE;
    public BlurryLayout(@NonNull Context context) {
        this(context,null);
    }

    public BlurryLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BlurryLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context,R.layout.blurry_layout,this);
        linearLayout= findViewById(R.id.linearLayout);
        imageView=findViewById(R.id.imageView);
        DEFAULT_IMAGE=getResources().getDrawable(R.drawable.image);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.BlurryLayout,defStyleAttr,0);
        BLUR_COLOR=typedArray.getColor(R.styleable.BlurryLayout_blurColor,DEFAULT_BLUR_COLOR);
        ALPHA= typedArray.getFloat(R.styleable.BlurryLayout_blurOpacity,DEFAULT_ALPHA);
        BLUR_RADIUS=typedArray.getInt(R.styleable.BlurryLayout_blurRadius,DEFAULT_BLUR_RADIUS);
        imageDrawable=typedArray.getDrawable(R.styleable.BlurryLayout_blurImage);
        typedArray.recycle();
        setImageBg();
    }
    // Set linear layout properties
    private void setImageBg(){
        Drawable bg;
        if (imageDrawable!=null){
            //To get a good effect from user image
           blurBackground(imageDrawable,BLUR_RADIUS);
        }else {
            bg=DEFAULT_IMAGE;
            Bitmap image= ((BitmapDrawable)bg).getBitmap();
            imageView.setImageBitmap(GaussianBlur.blurred(getContext(),image,BLUR_RADIUS));
        }

        linearLayout.setBackgroundColor(BLUR_COLOR);
        linearLayout.setAlpha(ALPHA);

    }

    public void blurColor(int blurColor){
        linearLayout.setBackgroundColor(blurColor);
    }
    public void blurOpacity(float bluropacity){
        linearLayout.setAlpha(bluropacity);
    }

    //Allow programmatic setting of image and blur radius
    public void blurBackground( Drawable imageDr,int radius) {
        Bitmap image=((BitmapDrawable)imageDr).getBitmap();
        int height=image.getHeight();
        int width=image.getWidth();
        Bitmap background=ThumbnailUtils.extractThumbnail(image,height*3/100,width*3/100);
        imageView.setImageBitmap(GaussianBlur.blurred(getContext(),background,radius));
    }
}
