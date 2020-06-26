package com.foodorderingapp.activity.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.admin.customerfragment.CustomerFragement;
import com.foodorderingapp.activity.sellingstaff.OrderFragement;
import com.foodorderingapp.activity.admin.productfragment.ProductFragement;
import com.foodorderingapp.activity.admin.promotionfragment.PromotionFragement;
import com.foodorderingapp.activity.admin.stafffragment.StaffFragement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminMainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        bottomNavigationView=findViewById(R.id.AdminButtonNav);
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.adminFragmentContainer,new ProductFragement()).commit();
        }
        //get Object- value from Intent
        final String taiKhoanId= getIntent().getSerializableExtra("taiKhoanId").toString();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment=null;
                switch (menuItem.getItemId()){
                    case R.id.adminproduct:
                        fragment=new ProductFragement();
                        break;
                    case R.id.adminpromotion:
                        fragment=new PromotionFragement();
                        break;
                    case R.id.admincustomer:
                        fragment=new CustomerFragement();
                        break;
                    case R.id.adminstaff:
                        fragment=new StaffFragement();
                        System.out.println("Tạo mới home Fragment");
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.adminFragmentContainer,fragment).commit();
                return true;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
