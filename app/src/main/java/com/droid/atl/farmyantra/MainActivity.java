package com.droid.atl.farmyantra;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //defining view objects
    Toolbar toolbar;
    FragmentManager fragmentTransaction;
    NavigationView navigationView;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // show home fragment upon start of app
        fragmentTransaction = getFragmentManager();
        fragmentTransaction.beginTransaction()
                .replace(R.id.content_frame, new ProductsFragment())
                .addToBackStack("ProductsFragment")
                .commit();
        getSupportActionBar().setTitle("Farm Yantra Products");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.login:
                fragmentTransaction.beginTransaction()
                        .replace(R.id.content_frame, new signup())
                        .addToBackStack("LoginFragment")
                        .commit();
                getSupportActionBar().setTitle("Company Signup");
                item.setChecked(true);
                drawer.closeDrawers();

                break;

            case R.id.MyAccount:

                break;

            case R.id.Tractors:

                break;

            case R.id.PowerTillers:

                break;

            case R.id.FieldPrep:

                break;

            case R.id.SowPlntr:

                break;

            case R.id.ThreshingMac:

                break;

            case R.id.Harvester:

                break;

            case R.id.Others:

                break;

            case R.id.nav_share:

                break;

            case R.id.nav_send:

                break;
            default:

                break;

        }


        return true;
    }
/*
    , MainFragment.OnFragmentInteractionListener
    @Override
    public void onFragmentInteraction(Uri uri) {

    }*/
}
