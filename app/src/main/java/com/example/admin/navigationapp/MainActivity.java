package com.example.admin.navigationapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Home Activity";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
            System.exit(0);
        }

        textView1 = (TextView) findViewById(R.id.main_textView1);
        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.menu_home):
                        drawerLayout.closeDrawers();
                        textView1.setText("Home");
                        break;
                    case (R.id.menu_personal):
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);

                        break;
                    case (R.id.menu_Photo):
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(MainActivity.this, ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    case (R.id.menu_upload):
                        drawerLayout.closeDrawers();
                        Intent intent1 = new Intent(MainActivity.this, PDFReader.class);
                        startActivity(intent1);
                        break;
                    case (R.id.menu_view):
                        drawerLayout.closeDrawers();
                        textView1.setText("View");
                        break;
                }
                return true;
            }
        });
        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.overflow_aboutus:
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.overflow_exit:
                finish();
                System.exit(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
