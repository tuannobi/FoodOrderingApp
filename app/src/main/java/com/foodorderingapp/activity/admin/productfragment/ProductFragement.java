package com.foodorderingapp.activity.admin.productfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.homefragment.DrinkFragment;
import com.foodorderingapp.activity.client.homefragment.FoodFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductFragement extends Fragment {
    BottomNavigationView bottomNavigationView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_fragment,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = (BottomNavigationView)view.findViewById(R.id.bottom_navigation1);
        if(savedInstanceState==null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1,new DrinkProductFragment()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.thucuong:
                        fragment = new DrinkProductFragment();
                        System.out.println("Thuc uong");
                        break;
                    case R.id.doan:
                        fragment = new FoodProductFragment();
                        System.out.println("Đồ ăn");
                        break;
                    case R.id.themthem:
                        fragment = new InsertItem();
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1,fragment).commit();
                return true;
            }
        });
    }
}
