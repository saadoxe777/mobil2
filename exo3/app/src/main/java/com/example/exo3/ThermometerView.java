package com.example.exo3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ThermometerView extends View {

    public void setScale(String scale) {
        this.scale = scale;
        invalidate();
    }
    private float temperature;
    private Paint paint;
    private String scale;

    public ThermometerView(Context context, String scale) {
        super(context);
        this.scale = scale;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        int mercuryHeight;
        switch(scale) {
            case "F":
                mercuryHeight = (int)(height * ((temperature * 1.8 + 32) / 212));
                break;
            case "K":
                mercuryHeight = (int)(height * ((temperature + 273.15) / 373.15));
                break;
            default: // Assume Celsius if scale is neither F nor K
                mercuryHeight = (int)(height * (temperature / 100));
                break;
        }

        if(temperature > 50) paint.setColor(Color.RED);
        else paint.setColor(Color.BLUE);

        canvas.drawRect(0, height - mercuryHeight, width, height, paint);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        invalidate();
    }

}
