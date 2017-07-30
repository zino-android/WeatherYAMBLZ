package com.zino.mobilization.weatheryamblz;

import android.support.test.espresso.ViewInteraction;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Denis Buzmakov on 29.07.17.
 * <buzmakov.da@gmail.com>
 */

public class SettingsFragmentTest extends BaseMainActivityTest {

    @Before
    public void init() {
        getDrawerLayout().perform(open());
        getNavigationView().perform(navigateTo(R.id.nav_settings));
    }

    @Test
    public void shouldChangeTemperatureUnits() {
        getCelsiusButton().check(matches(isDisplayed()));
        getFahrenheitButton().check(matches(isDisplayed()));

        getCelsiusButton().perform(click());
        getCelsiusButton().check(matches(not(isEnabled())));
        getFahrenheitButton().check(matches(isEnabled()));

        getFahrenheitButton().perform(click());
        getCelsiusButton().check(matches(isEnabled()));
        getFahrenheitButton().check(matches(not(isEnabled())));
    }

    private ViewInteraction getCelsiusButton() {
        return onView(withId(R.id.celsius_button));
    }

    private ViewInteraction getFahrenheitButton() {
        return onView(withId(R.id.fahrenheit_button));
    }

}
