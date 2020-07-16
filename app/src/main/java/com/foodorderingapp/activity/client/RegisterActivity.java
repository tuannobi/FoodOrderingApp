package com.foodorderingapp.activity.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodorderingapp.R;
import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.TaiKhoan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private EditText hoTen;
    private EditText Sdt;
    private EditText diaChi;
    private EditText tenDangNhap;
    private EditText matKhau;
    private EditText reMatKhau;
    private EditText email;
    private Button registerBtn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        hoTen=findViewById(R.id.hoTenEditText);
        Sdt=findViewById(R.id.SdtEditText);
        diaChi=findViewById(R.id.diaChiEditText);
        tenDangNhap=findViewById(R.id.usernameEditText);
        matKhau=findViewById(R.id.passwordEditText);
        reMatKhau=findViewById(R.id.repasswordEditText);
        email=findViewById(R.id.EmailEditText);
        registerBtn=findViewById(R.id.registerButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempHoTen=hoTen.getText().toString();
                final String[] hotenarray=tempHoTen.split(" ");
                final String tempSdt=Sdt.getText().toString();
                final String diaChiTemp=diaChi.getText().toString();
                String tenDangNhaptemp=tenDangNhap.getText().toString();
                String tempMatKhau=matKhau.getText().toString();
                String tempReMatKhau=reMatKhau.getText().toString();
                final String tempEmail=email.getText().toString();

                if(tempMatKhau.equals(tempReMatKhau)==false){
                    Toast.makeText(getApplicationContext(),"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                }else{
                    TaiKhoan taiKhoan=new TaiKhoan();
                    taiKhoan.setUserName(tenDangNhaptemp);
                    taiKhoan.setPassword(tempMatKhau);
                    taiKhoan.setVaiTroId("Eqd34FqcI5tZfxqpDhWY");
                    taiKhoan.setNgayTao(new Date());
                    taiKhoan.setKichHoat(1);
                    db.collection("TaiKhoan")
                            .add(taiKhoan)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    DocumentReference washingtonRef = db.collection("TaiKhoan").document(documentReference.getId());

// Set the "isCapital" field of the city 'DC'
                                    washingtonRef
                                            .update("taiKhoanId", documentReference.getId())
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("TAG", "DocumentSnapshot successfully updated!");

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("TAG", "Error updating document", e);
                                                }
                                            });
                                    KhachHang khachHang=new KhachHang();
                                    khachHang.setHo(hotenarray[0]);
                                    khachHang.setTen(hotenarray[1]);
                                    khachHang.setSDT(tempSdt);
                                    khachHang.setDiaChi(diaChiTemp);
                                    khachHang.setTaiKhoanId(documentReference.getId());
                                    khachHang.setEmail(tempEmail);
                                    db.collection("TaiKhoan")
                                            .add(khachHang)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
                                                    DocumentReference washingtonRef = db.collection("KhachHang").document("DC");

// Set the "isCapital" field of the city 'DC'
                                                    washingtonRef
                                                            .update("khachHangId", documentReference.getId())
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    Log.d("TAG", "DocumentSnapshot successfully updated!");
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Log.w("TAG", "Error updating document", e);
                                                                }
                                                            });
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("TAG", "Error adding document", e);
                                                }
                                            });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error adding document", e);
                                }
                            });
                            finish();
                }
            }
        });
    }
}
