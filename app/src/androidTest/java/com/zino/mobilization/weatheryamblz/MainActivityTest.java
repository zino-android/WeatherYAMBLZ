package com.zino.mobilization.weatheryamblz;

import android.os.SystemClock;

import org.junit.Test;

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

public class MainActivityTest extends BaseMainActivityTest {

    @Test
    public void drawerShouldCloseAfterItemClick() {
        getDrawerLayout().check(matches(isClosed()))
                .perform(open());
        getDrawerLayout().check(matches(isOpen()));
        getNavigationView().perform(navigateTo(R.id.nav_about));
        SystemClock.sleep(500); //wait to close drawer
        getDrawerLayout().check(matches(isClosed()));
    }

    @Test
    public void shouldCloseDrawerAfterBackPressed() {
        getDrawerLayout().check(matches(isClosed()))
                .perform(open());
        pressBack();
        SystemClock.sleep(500);//wait to close drawer
        getDrawerLayout().check(matches(isClosed()));
    }

    @Test
    public void shouldGoToSettings() {
        getDrawerLayout().check(matches(isClosed()))
                .perform(open());
        getNavigationView().perform(navigateTo(R.id.nav_settings));
        onView(withId(R.id.layout_settings)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldGoToAbout() {
        getDrawerLayout().check(matches(isClosed()))
                .perform(open());
        getNavigationView().perform(navigateTo(R.id.nav_about));
        onView(withId(R.id.layout_about)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldGoToWeather() {
        getDrawerLayout().check(matches(isClosed()))
                .perform(open());
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
        getNavigationView().perform(navigateTo(R.id.nav_settings));
        getDrawerLayout().perform(open());
        getNavigationView().perform(navigateTo(R.id.nav_weather));
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
    }
}
