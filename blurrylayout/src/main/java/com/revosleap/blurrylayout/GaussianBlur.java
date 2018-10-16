package com.revosleap.blurrylayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;


class GaussianBlur {
    private static Bitmap blurry;


    public static Bitmap blurred(Context context, Bitmap bitmap, int radius){
        try {
            bitmap=colorEncodingChange(bitmap);

            }
            catch (Exception e){
            e.printStackTrace();

            }
        try {
             blurry= Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888);
        }catch (Exception e){
            e.printStackTrace();
        }
        RenderScript renderScript= RenderScript.create(context);
        Allocation blurInput= Allocation.createFromBitmap(renderScript,bitmap);
        Allocation blurOutput= Allocation.createFromBitmap(renderScript,blurry);
        ScriptIntrinsicBlur blur= ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius);
        blur.forEach(blurOutput);
        blurOutput.copyTo(bitmap);
        renderScript.destroy();
        return bitmap;
    }
    private static Bitmap colorEncodingChange(Bitmap img)throws Exception {
        int pixelNum= img.getWidth()/4*img.getHeight()/4;
        int[] pixels= new int[pixelNum];
        img.getPixels(pixels,0,img.getWidth(),0,0,img.getWidth(),img.getHeight());
        Bitmap result= Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.ARGB_8888);
        result.setPixels(pixels,0,result.getWidth()/4,0,0,result.getWidth()/4,result.getHeight());
        return result;
    }

}
