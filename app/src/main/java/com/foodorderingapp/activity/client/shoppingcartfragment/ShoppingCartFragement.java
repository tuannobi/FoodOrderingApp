package com.foodorderingapp.activity.client.shoppingcartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;

public class ShoppingCartFragement extends Fragment {
    private String taiKhoanId;

    public ShoppingCartFragement(String taiKhoanId){
        this.taiKhoanId=taiKhoanId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("Mã tài khoản của khách hàng vừa đăng nhập là: "+this.taiKhoanId);
        return inflater.inflate(R.layout.shoppingcart_fragment,container,false);
    }
}
