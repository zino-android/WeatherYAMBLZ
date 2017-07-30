package com.zino.mobilization.weatheryamblz.common;

import android.net.Uri;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.zino.mobilization.weatheryamblz.model.pojo.City;
import com.zino.mobilization.weatheryamblz.model.pojo.WeatherResponse;

import java.util.List;
import java.util.Locale;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

public class TestData {

    public static WeatherResponse getMoscowResponse() {
        return new Gson().fromJson(getMoscowWeatherJson(), WeatherResponse.class);
    }

    public static WeatherResponse getPermResponse() {
        return new Gson().fromJson(getPermWeatherJson(), WeatherResponse.class);
    }

    public static City getNewYork() {
        return new City("New York", 40.730610, -73.935242);
    }

    private static String getMoscowWeatherJson() {
        return "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 37.62,\n" +
                "        \"lat\": 55.75\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 802,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"scattered clouds\",\n" +
                "            \"icon\": \"03d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 300.91,\n" +
                "        \"pressure\": 1007,\n" +
                "        \"humidity\": 51,\n" +
                "        \"temp_min\": 300.15,\n" +
                "        \"temp_max\": 302.15\n" +
                "    },\n" +
                "    \"visibility\": 10000,\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 6,\n" +
                "        \"deg\": 260\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 40\n" +
                "    },\n" +
                "    \"dt\": 1501158600,\n" +
                "    \"sys\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": 7325,\n" +
                "        \"message\": 0.0044,\n" +
                "        \"country\": \"RU\",\n" +
                "        \"sunrise\": 1501118798,\n" +
                "        \"sunset\": 1501177452\n" +
                "    },\n" +
                "    \"id\": 524901,\n" +
                "    \"name\": \"Moscow\",\n" +
                "    \"cod\": 200\n" +
                "}";
    }

    private static String getPermWeatherJson() {
        return "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 56.29,\n" +
                "        \"lat\": 58.02\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 800,\n" +
                "            \"main\": \"Clear\",\n" +
                "            \"description\": \"clear sky\",\n" +
                "            \"icon\": \"02d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 294.845,\n" +
                "        \"pressure\": 1001.94,\n" +
                "        \"humidity\": 78,\n" +
                "        \"temp_min\": 294.845,\n" +
                "        \"temp_max\": 294.845,\n" +
                "        \"sea_level\": 1022.12,\n" +
                "        \"grnd_level\": 1001.94\n" +
                "    },\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 2.47,\n" +
                "        \"deg\": 320.504\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 8\n" +
                "    },\n" +
                "    \"dt\": 1501162005,\n" +
                "    \"sys\": {\n" +
                "        \"message\": 0.0051,\n" +
                "        \"country\": \"RU\",\n" +
                "        \"sunrise\": 1501113521,\n" +
                "        \"sunset\": 1501173756\n" +
                "    },\n" +
                "    \"id\": 511196,\n" +
                "    \"name\": \"Perm\",\n" +
                "    \"cod\": 200\n" +
                "}";
    }

    public static Place getTestPlace() {
        return new Place() {
            @Override
            public String getId() {
                return null;
            }

            @Override
            public List<Integer> getPlaceTypes() {
                return null;
            }

            @Override
            public CharSequence getAddress() {
                return "Moscow, Russia";
            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public CharSequence getName() {
                return null;
            }

            @Override
            public LatLng getLatLng() {
                return new LatLng(57, 58);
            }

            @Override
            public LatLngBounds getViewport() {
                return null;
            }

            @Override
            public Uri getWebsiteUri() {
                return null;
            }

            @Override
            public CharSequence getPhoneNumber() {
                return null;
            }

            @Override
            public float getRating() {
                return 0;
            }

            @Override
            public int getPriceLevel() {
                return 0;
            }

            @Override
            public CharSequence getAttributions() {
                return null;
            }

            @Override
            public Place freeze() {
                return null;
            }

            @Override
            public boolean isDataValid() {
                return false;
            }
        };
    }

    public static City getTestCity() {
        return new City("", 55, 51);
    }

}
