package com.zino.mobilization.weatheryamblz;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zino.mobilization.weatheryamblz.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Denis Buzmakov on 28.07.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void drawerShouldCloseAfterItemClick() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()))
                .perform(open());
        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_about));
        SystemClock.sleep(500); //wait to close drawer
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
    }

    @Test
    public void shouldCloseDrawerAfterBackPressed() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()))
                .perform(open());
        pressBack();
        SystemClock.sleep(500);//wait to close drawer
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
    }

    @Test
    public void shouldGoToSettings() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()))
                .perform(open());
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_settings));
        onView(withId(R.id.layout_settings)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldGoToAbout() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()))
                .perform(open());
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_about));
        onView(withId(R.id.layout_about)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldGoToWeather() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()))
                .perform(open());
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_settings));
        onView(withId(R.id.drawer_layout)).perform(open());
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_weather));
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
    }
}
