package com.revosleap.blurlayout.Blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.revosleap.blurlayout.R;


public class BlurLayout extends FrameLayout {
    private ConstraintLayout constraintLayout;
    private ImageView imageView;

    public BlurLayout( @NonNull Context context) {
        this(context,null);
    }

    public BlurLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BlurLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context,R.layout.blurry_layout,this);
        constraintLayout= findViewById(R.id.constraintLayout);
        imageView=findViewById(R.id.imageView);
        constraintLayout.setBackgroundColor(Color.WHITE);
        constraintLayout.setAlpha(0.3f);

    }
    public void blurBackground(Bitmap image,int blurRadius){
        int height=image.getHeight();
       int width=image.getWidth();
       Bitmap background=ThumbnailUtils.extractThumbnail(image,height*3/100,width*3/100);
        imageView.setImageBitmap(GaussianBlur.blurred(getContext(),background,blurRadius));
    }
    public void blurColor(int blurColor){
        constraintLayout.setBackgroundColor(blurColor);
    }
    public void blurOpacity(float bluropacity){
        constraintLayout.setAlpha(bluropacity);
    }

}
