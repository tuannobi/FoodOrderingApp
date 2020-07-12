package com.foodorderingapp.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.admin.commentfragment.CommentMain;
import com.foodorderingapp.activity.admin.customerfragment.CustomerMain;
import com.foodorderingapp.activity.admin.orderfragment.OrderMain;
import com.foodorderingapp.activity.admin.productfragment.ProductMain;
import com.foodorderingapp.activity.admin.promotionfragment.PromotionMain;
import com.google.android.material.navigation.NavigationView;

public class AdminMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        Menu menu=navigationView.getMenu();
        menu.findItem(R.id.nav_logouot).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.product_management);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent=null;
        switch (menuItem.getItemId()){
            case R.id.promotion_management:
                intent=new Intent(AdminMainActivity.this, PromotionMain.class);
                startActivity(intent);
                break;
            case R.id.product_management:
                intent=new Intent(AdminMainActivity.this, ProductMain.class);
                startActivity(intent);
                break;
            case R.id.order_management:
                intent=new Intent(AdminMainActivity.this, OrderMain.class);
                startActivity(intent);
                break;
            case R.id.comment_management:
                intent=new Intent(AdminMainActivity.this, CommentMain.class);
                startActivity(intent);
                break;
            case R.id.customer_management:
                intent=new Intent(AdminMainActivity.this, CustomerMain.class);
                startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
