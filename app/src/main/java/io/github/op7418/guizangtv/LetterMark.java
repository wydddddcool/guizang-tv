package io.github.op7418.guizangtv;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

/** Monochrome letter tile for entries that have no dedicated mark. */
final class LetterMark extends Drawable {
    private final String label;
    private final float density;
    private final Paint stroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint text = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF box = new RectF();

    LetterMark(String label, int color, float density) {
        this.label = label;
        this.density = density;
        stroke.setStyle(Paint.Style.STROKE);
        stroke.setStrokeWidth(1.6f * density);
        stroke.setColor(color);
        text.setColor(color);
        text.setTypeface(Typeface.DEFAULT_BOLD);
        text.setTextAlign(Paint.Align.CENTER);
    }

    @Override public void draw(Canvas canvas) {
        Rect b = getBounds();
        float inset = 1.8f * density;
        box.set(b.left + inset, b.top + inset, b.right - inset, b.bottom - inset);
        float r = Math.min(box.width(), box.height()) * 0.28f;
        canvas.drawRoundRect(box, r, r, stroke);
        text.setTextSize(box.width() * 0.5f);
        Paint.FontMetrics fm = text.getFontMetrics();
        canvas.drawText(label, box.centerX(), box.centerY() - (fm.ascent + fm.descent) / 2f, text);
    }

    @Override public void setAlpha(int alpha) { stroke.setAlpha(alpha); text.setAlpha(alpha); }
    @Override public void setColorFilter(ColorFilter cf) { stroke.setColorFilter(cf); text.setColorFilter(cf); }
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
}
