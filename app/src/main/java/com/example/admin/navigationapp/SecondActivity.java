package com.example.admin.navigationapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Personal Activity";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        drawerLayout  = (DrawerLayout) findViewById(R.id.drawerLayout_PersonalInfo);
        actionBarDrawerToggle = new ActionBarDrawerToggle(SecondActivity.this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case (R.id.menu_home):
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case (R.id.menu_personal):
                        drawerLayout.closeDrawers();

                        break;
                    case (R.id.menu_Photo):
                        Intent intent2 = new Intent(SecondActivity.this, ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    case (R.id.menu_upload):
                        drawerLayout.closeDrawers();
                        break;
                    case (R.id.menu_view):
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.overflow_aboutus:
                Intent intent = new Intent(SecondActivity.this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.overflow_exit:
                Intent intent5 = new Intent(SecondActivity.this, MainActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent5.putExtra("EXIT", true);
                startActivity(intent5);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
