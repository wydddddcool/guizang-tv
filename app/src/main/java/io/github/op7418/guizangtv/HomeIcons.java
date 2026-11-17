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
            case "com.mitv.gallery":case "com.xiaomi.tv.gallery":resource=R.drawable.mark_image;break;
            case "com.mitv.tvhome":resource=R.drawable.mark_house;break;
            case "com.quark.yun.tv":resource=R.drawable.mark_cloud;break;
            case "cn.miguvideo.migutv":resource=R.drawable.mark_mic;break;
            case "com.newtv.cboxtv":resource=R.drawable.mark_tv;break;
            case "com.cibn.tv":resource=R.drawable.mark_cat;break;
            case "control:assistant":resource=R.drawable.mark_chat_circle_dots;break;
            case "control:settings":resource=R.drawable.mark_gear_six;break;
            case "control:inputs":resource=R.drawable.mark_monitor_play;break;
            case "control:apps":resource=R.drawable.mark_squares_four;break;
            default:
                if(id.startsWith("hdmi:")){resource=R.drawable.mark_monitor_play;break;}
                return letter(context,id,color);
        }
        Drawable mark=context.getDrawable(resource).mutate();mark.setTint(color);return mark;
    }

    /** Every unknown app keeps the monochrome set: a thin frame with its first character. */
    private static Drawable letter(Context context,String id,int color){
        String label=id;
        try{
            PackageManager pm=context.getPackageManager();
            label=pm.getApplicationLabel(pm.getApplicationInfo(id,0)).toString();
        }catch(PackageManager.NameNotFoundException|RuntimeException unavailable){
            label=id;
        }
        String first=label.isEmpty()?"?":label.substring(0,Math.min(2,label.length()));
        return new LetterMark(first,color,context.getResources().getDisplayMetrics().density);
    }
}
