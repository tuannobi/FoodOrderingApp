package com.foodorderingapp.activity.admin.customerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;
import com.foodorderingapp.model.KhachHang;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class Custom_detail extends AppCompatActivity {
    private String email;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView Textngsinh;
    TextView Texthotenkhachhang;
    TextView Textdoanhthu;
    TextView Textemail;
    TextView Textdchi;
    TextView Textsdt;
    KhachHang khachHang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_detail);
        this.email = (String) getIntent().getSerializableExtra("email");
        System.out.println("email của khách hàng vừa đăng nhập là: " + this.email);
        db.collection("KhachHang")
                .whereEqualTo("email", email)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }

                        for (QueryDocumentSnapshot doc : value) {
                            khachHang = doc.toObject(KhachHang.class);
                            break;
                        }
                        System.out.println("Khách hàng: " + khachHang.toString());


                        Textngsinh = (TextView) findViewById(R.id.ngaysinhkhachhang);
                        Texthotenkhachhang = (TextView) findViewById(R.id.hotenkhachhang);
                        Textdchi = (TextView) findViewById(R.id.diachikhachhang);
                        Textemail = (TextView) findViewById(R.id.emailkhachhang);
                        Textsdt = (TextView) findViewById(R.id.sdtkhachhang);
                        Textdoanhthu = (TextView) findViewById(R.id.doanhthukhachhang);

                        //Hiện dữ liệu

                        System.out.println(khachHang.toString());

                       Texthotenkhachhang.setText("Họ tên: " + khachHang.getHo() + " " + khachHang.getTen());
                        Textdchi.setText("Địa chỉ: "+khachHang.getDiaChi());
                        Textemail.setText("Email: "+khachHang.getEmail());
                        Textsdt.setText("Số điện thoại :"+khachHang.getSDT());

                       Textdoanhthu.setText("Doanh thu: "+ khachHang.getDoanhThu());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        Textngsinh.setText("Ngày sinh: "+simpleDateFormat.format(khachHang.getNgaySinh()));
                        Button buttondong=(Button) findViewById(R.id.dongchitietkhachhang);
                        buttondong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();

                            }});

                    }

                });
    }
}
