package com.example.movietimesuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.movietimesuas.Viewpager.Pager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBar));

        TabLayout tabLayout = findViewById(R.id.tabLayoutAndroid);
        ViewPager viewPager = findViewById(R.id.viewPager);
        Pager pager = new Pager(getSupportFragmentManager());
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);
    }
}