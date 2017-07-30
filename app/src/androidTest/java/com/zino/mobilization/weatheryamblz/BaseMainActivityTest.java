package com.zino.mobilization.weatheryamblz;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zino.mobilization.weatheryamblz.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(AndroidJUnit4.class)
public abstract class BaseMainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);


    ViewInteraction getDrawerLayout() {
        return onView(withId(R.id.drawer_layout));
    }

    ViewInteraction getNavigationView() {
        return onView(withId(R.id.nav_view));
    }
}
