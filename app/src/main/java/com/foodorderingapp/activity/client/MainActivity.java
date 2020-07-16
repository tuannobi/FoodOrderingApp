package com.foodorderingapp.activity.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.homefragment.HomeFragement;
import com.foodorderingapp.activity.client.mefragment.MeFragement;
//import com.foodorderingapp.activity.client.myordersfragment.MyOrdersFragement;
import com.foodorderingapp.activity.client.myordersfragment.MyOrdersFragement;
import com.foodorderingapp.activity.client.shoppingcartfragment.ShoppingCartFragement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.buttonNav);
        final String taiKhoanId= getIntent().getSerializableExtra("taiKhoanId").toString();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragement(taiKhoanId)).commit();
        }
        //get Object- value from Intent

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment=null;
                switch (menuItem.getItemId()){
                    case R.id.myhome:
                        fragment=new HomeFragement(taiKhoanId);
                        break;
                    case R.id.myorders:
                        fragment=new MyOrdersFragement(taiKhoanId);
                        break;
                    case R.id.shoppingcart:
                        fragment=new ShoppingCartFragement(taiKhoanId);
                        break;
                    case R.id.me:
                        fragment=new MeFragement(taiKhoanId);
                        System.out.println("Tạo mới home Fragment");
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
