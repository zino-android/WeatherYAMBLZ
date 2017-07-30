package com.zino.mobilization.weatheryamblz.utils;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by Denis Buzmakov on 21.07.2017.
 * <buzmakov.da@gmail.com>
 */

public class UtilsTest {

    @Test
    public void convertFromCelsiusToFahrenheitTest() {
        assertEquals(32d, Utils.celsiusToFahrenheit(0));
        assertEquals(50, Math.round(Utils.celsiusToFahrenheit(10)));
        assertEquals(14, Math.round(Utils.celsiusToFahrenheit(-10)));
        assertEquals(91, Math.round(Utils.celsiusToFahrenheit(33)));
    }

    @Test
    public void convertFromMetersToKilometersTest() {
        assertEquals(5, Utils.metersToKm(5200));
        assertEquals(0, Utils.metersToKm(300));
        assertEquals(-3, Utils.metersToKm(-2598));
    }

    @Test
    public void formatTemperatureTest() {
        assertEquals("25°", Utils.formatTemperature(25));
        assertEquals("25°", Utils.formatTemperature(25.349657));
        assertEquals("26°", Utils.formatTemperature(25.949657));
        assertEquals("-25°", Utils.formatTemperature(-25));
        assertEquals("0°", Utils.formatTemperature(0.499999));
    }
}