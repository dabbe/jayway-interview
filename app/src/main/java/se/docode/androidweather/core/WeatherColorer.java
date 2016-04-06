package se.docode.androidweather.core;

import android.graphics.Color;

/**
 * Created by Daniel on 2016-04-06.
 */
public class WeatherColorer {


    public static int getColor(double temp) {

        float[] hsv = new float[3];
        hsv[1] = 1;
        hsv[2] = 1;


        // y = kx + m, calculated with hue = 0 when it is 278 degrees K and hue = 50 when it is 308 degrees K
        hsv[0] = temp < 278 ? 215 : (float)(temp * (-5/3f) + 510);
        return Color.HSVToColor(210, hsv);
    }
}
