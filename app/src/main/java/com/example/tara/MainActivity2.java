package com.example.tara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager viewPager;
    TabLayout tablayout;
    TabItem firstitem, seconditem, thirditem;
    PagerAdapter adapter;
    Button signin;
    private FirebaseAuth Firebaseauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = findViewById(R.id.viewpager);
        tablayout = findViewById(R.id.tablayout);

        firstitem = findViewById(R.id.firstitem);
        seconditem = findViewById(R.id.seconditem);
        thirditem = findViewById(R.id.thirditem);
        signin = findViewById(R.id.sigin);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_veiw);
        navigationView.setNavigationItemSelectedListener(this);
        Firebaseauth= FirebaseAuth.getInstance();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();





        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tablayout.getTabCount());
        viewPager.setAdapter(adapter);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() ==R.id.sigin){
            Intent myintent = new Intent(getApplicationContext(), signup.class);
            startActivity(myintent);
        }
        else if(item.getItemId() ==R.id.nav_home){
            Intent myintent = new Intent(getApplicationContext(), home.class);
            startActivity(myintent);
        }
       else if(item.getItemId() ==R.id.nav_about){
            Intent myintent = new Intent(getApplicationContext(), about.class);
            startActivity(myintent);
        }
       else if(item.getItemId() ==R.id.nav_help){
            Intent myintent = new Intent(getApplicationContext(), help.class);
            startActivity(myintent);
        }
       else if(item.getItemId() ==R.id.nav_feedback) {
            Intent myintent = new Intent(getApplicationContext(), feedback.class);
            startActivity(myintent);
        }
       else if(item.getItemId() ==R.id.nav_privacypolicy){
                Intent myintent = new Intent(getApplicationContext(), privacypolicy.class);
                startActivity(myintent);
            }
       else if(item.getItemId() ==R.id.logout){
            Intent myintent = new Intent(getApplicationContext(),login.class);
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(myintent);

        }
        return false;
    }
}
