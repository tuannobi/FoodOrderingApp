package com.foodorderingapp.activity.client.homefragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.foodorderingapp.R;
import com.foodorderingapp.adapter.SanPhamAdapter;
import com.foodorderingapp.dialog.DialogItem;
import com.foodorderingapp.model.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragement extends Fragment {

    private String taiKhoanId;
    BottomNavigationView bottomNavigationView;

    public HomeFragement(String taiKhoanId) {
        this.taiKhoanId=taiKhoanId;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("Mã tài khoản của khách hàng vừa đăng nhập là: "+this.taiKhoanId);
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = (BottomNavigationView)view.findViewById(R.id.bottom_navigation);
        if(savedInstanceState==null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new DrinkFragment(taiKhoanId)).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.thucuong:
                        fragment = new DrinkFragment(taiKhoanId);
                        System.out.println("Thuc uong");
                        break;
                    case R.id.doan:
                        fragment = new FoodFragment(taiKhoanId);
                        System.out.println("Đồ ăn");
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
                return true;
            }
        });
    }



}