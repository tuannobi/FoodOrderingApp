package com.foodorderingapp.activity.client.myordersfragment.lichsudonhangfragment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.admin.customerfragment.CustomerFragement;
import com.foodorderingapp.activity.admin.productfragment.ProductFragement;
import com.foodorderingapp.activity.admin.promotionfragment.PromotionFragement;
import com.foodorderingapp.activity.admin.stafffragment.StaffFragement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChiTietDonHangActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdonhang_row);


    }


}
