package com.revosleap.blurrylayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
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
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.setAlpha(0.3f);

    }
    public void blurBackground(Bitmap image,int blurRadius){
        int height=image.getHeight();
       int width=image.getWidth();
       Bitmap background=ThumbnailUtils.extractThumbnail(image,height*3/100,width*3/100);
        imageView.setImageBitmap(GaussianBlur.blurred(getContext(),background,blurRadius));
    }
    public void blurColor(int blurColor){
        linearLayout.setBackgroundColor(blurColor);
    }
    public void blurOpacity(float bluropacity){
        linearLayout.setAlpha(bluropacity);
    }

}
