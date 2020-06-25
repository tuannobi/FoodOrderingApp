package com.foodorderingapp.crud.taikhoan;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;
import com.foodorderingapp.model.TaiKhoan;
import com.foodorderingapp.model.VaiTro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadTaiKhoan extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db.collection("TaiKhoan")
                .whereEqualTo("userName","tuannobi")
                .whereEqualTo("password","1234567")
                // .whereEqualTo("password",passwordEditText.toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            TaiKhoan taikhoan=null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                taikhoan=document.toObject(TaiKhoan.class);
                            }
                            if(taikhoan==null){
                                Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                                System.out.println("That bai");
                            }else{
                                System.out.println("Thanh cong");
                                System.out.println(taikhoan.toString());
                            }
                        }
                    }
                });
    }
}
