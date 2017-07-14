package com.zino.mobilization.weatheryamblz;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zino.mobilization.weatheryamblz.fragments.AboutFragment;
import com.zino.mobilization.weatheryamblz.fragments.SettingsFragment;
import com.zino.mobilization.weatheryamblz.fragments.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TITLE_KEY = "title_key";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new WeatherFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_weather);
            toolbar.setTitle(getResources().getString(R.string.action_weather));
        } else {
            String title = savedInstanceState.getString(TITLE_KEY);
            toolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_weather:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new WeatherFragment()).commit();
                toolbar.setTitle(getResources().getString(R.string.action_weather));
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();
                toolbar.setTitle(getResources().getString(R.string.action_about));
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
                toolbar.setTitle(getResources().getString(R.string.action_settings));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, toolbar.getTitle().toString());
    }

}
