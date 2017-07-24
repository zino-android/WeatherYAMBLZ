package com.zino.mobilization.weatheryamblz.utils;

import com.zino.mobilization.weatheryamblz.R;

import junit.framework.Assert;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by Denis Buzmakov on 21.07.2017.
 * <buzmakov.da@gmail.com>
 */

public class IconConditionWeatherTest {

    @Test
    public void shouldReturnSunIcon() {
        assertEquals(R.drawable.sun, Utils.getImageIdByName("01d"));
        assertEquals(R.drawable.sun, Utils.getImageIdByName("01n"));
    }

    @Test
    public void shouldReturnCloudIcon() {
        assertEquals(R.drawable.cloud, Utils.getImageIdByName("03d"));
        assertEquals(R.drawable.cloud, Utils.getImageIdByName("03n"));
        assertEquals(R.drawable.cloud, Utils.getImageIdByName("04d"));
        assertEquals(R.drawable.cloud, Utils.getImageIdByName("04n"));
    }

    @Test
    public void shouldReturnCloudSunIcon() {
        assertEquals(R.drawable.cloud_sun, Utils.getImageIdByName("02d"));
        assertEquals(R.drawable.cloud_sun, Utils.getImageIdByName("02n"));
    }

    @Test
    public void shouldReturnMistIcon() {
        assertEquals(R.drawable.mist, Utils.getImageIdByName("50d"));
        assertEquals(R.drawable.mist, Utils.getImageIdByName("50d"));
    }

    @Test
    public void shouldReturnRainIcon() {
        assertEquals(R.drawable.rain, Utils.getImageIdByName("10d"));
        assertEquals(R.drawable.rain, Utils.getImageIdByName("10n"));
    }

    @Test
    public void shouldReturnShowerRainIcon() {
        assertEquals(R.drawable.shower_rain, Utils.getImageIdByName("09d"));
        assertEquals(R.drawable.shower_rain, Utils.getImageIdByName("09n"));
    }

    @Test
    public void shouldReturnStormIcon() {
        assertEquals(R.drawable.thunderstorm, Utils.getImageIdByName("11d"));
        assertEquals(R.drawable.thunderstorm, Utils.getImageIdByName("11n"));
    }

    @Test
    public void shouldReturnSnowIcon() {
        Assert.assertEquals(R.drawable.snow, Utils.getImageIdByName("13d"));
        assertEquals(R.drawable.snow, Utils.getImageIdByName("13n"));
    }

    @Test
    public void shouldReturnNullIcon() {
        assertEquals(0, Utils.getImageIdByName(null));
        assertEquals(0, Utils.getImageIdByName("azaza"));
    }
}
