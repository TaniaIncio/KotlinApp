package com.tincio.pharmaapp.presentation.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Images {
    public static Drawable getDrawableByName(Context context,String name){
        Drawable drawable = null;
        try{
            int resourceId = context.getResources().getIdentifier(name,"drawable",context.getPackageName());
            drawable =  context.getResources().getDrawable(resourceId);
        }catch(Exception e){
            throw e;
        }
        return drawable;
    }
}
