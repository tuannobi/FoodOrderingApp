package com.foodorderingapp.crud.khachhang;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;
import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.TaiKhoan;
import com.foodorderingapp.model.VaiTro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;

public class Register extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create TAiKhoan
        String vaiTroID="Eqd34FqcI5tZfxqpDhWY";
        TaiKhoan taiKhoan=new TaiKhoan(null,"tuannobi","123456",new Date(2/1/2000),1,vaiTroID);
        //add new TaiKhoan to Firebase
        db.collection("TaiKhoan")
                .add(taiKhoan)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //update TaiKhoanId
                        documentReference.update("taiKhoanId",documentReference.getId());
                        Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                        //create KhachHang
                        KhachHang khachHang=new KhachHang(null,"Tran","Tuan",new Date(20/10/2000),"tuan@gmail.com","0123451045",
                                "dia chi",documentReference.getId());
                        db.collection("KhachHang")
                                .add(khachHang)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        documentReference.update("khachHangId",documentReference.getId());
                                        Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
