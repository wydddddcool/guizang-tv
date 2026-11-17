package io.github.op7418.guizangtv;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/** Monochrome SVG marks, converted losslessly to Android vectors. See assets/icons/SOURCES.json. */
final class HomeIcons {
    static Drawable get(Context context,String id,int color){
        int resource;
        switch(id){
            case "hdmi:1":resource=R.drawable.mark_playstation;break;
            case "hdmi:2":resource=R.drawable.mark_switch;break;
            case "com.xiaodianshi.tv.yst":resource=R.drawable.mark_bilibili;break;
            case "com.xiaomi.mitv.smartshare":resource=R.drawable.mark_broadcast;break;
            case "com.mitv.gallery":resource=R.drawable.mark_image;break;
            case "com.mitv.tvhome":resource=R.drawable.mark_house;break;
            case "control:assistant":resource=R.drawable.mark_chat_circle_dots;break;
            case "control:settings":resource=R.drawable.mark_gear_six;break;
            case "control:inputs":resource=R.drawable.mark_monitor_play;break;
            case "control:apps":resource=R.drawable.mark_squares_four;break;
            default:
                Drawable app=appIcon(context,id);
                if(app!=null)return app;
                resource=id.startsWith("hdmi:")?R.drawable.mark_monitor_play:R.drawable.mark_app_window;
        }
        Drawable mark=context.getDrawable(resource).mutate();mark.setTint(color);return mark;
    }

    /** Installed apps use their own launcher icon instead of the generic window mark. */
    private static Drawable appIcon(Context context,String id){
        if(id.startsWith("hdmi:")||id.startsWith("control:")||!id.contains("."))return null;
        try{
            Drawable icon=context.getPackageManager().getApplicationIcon(id);
            return icon==null?null:icon.mutate();
        }catch(PackageManager.NameNotFoundException|RuntimeException unavailable){
            return null;
        }
    }
}
