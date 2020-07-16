package com.foodorderingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.admin.AdminMainActivity;
import com.foodorderingapp.activity.client.MainActivity;
import com.foodorderingapp.activity.client.RegisterActivity;
import com.foodorderingapp.model.TaiKhoan;
import com.foodorderingapp.model.VaiTro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerTextView;
    private static final String CUSTOMER_ROLE="Khách hàng";
    private static final String SELLINGSTAFF_ROLE="Nhân viên bán hàng";
    private static final String SHIPPINGSTAFF_ROLE="Nhân viên giao hàng";
    private static final String ADMIN_ROLE="Admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        loginButton=findViewById(R.id.loginButton);
        registerTextView=findViewById(R.id.registerTextView);
        loginButtonEvent();
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }

    private void registerEvent() {

    }

    private void loginButtonEvent(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("TaiKhoan")
                        .whereEqualTo("userName",userNameEditText.getText().toString())
                        .whereEqualTo("password",passwordEditText.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    TaiKhoan taiKhoan=null;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        taiKhoan=document.toObject(TaiKhoan.class);
                                        break;
                                    }
                                    if(taiKhoan==null){
                                        Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                                    }else{
                                        //Kiểm tra vai trò và mở các activity tương ứng
                                        final TaiKhoan finalTaiKhoan = taiKhoan;
                                        db.collection("VaiTro")
                                                .whereEqualTo("vaiTroId",taiKhoan.getVaiTroId())
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if(task.isSuccessful()){
                                                            VaiTro vaiTro=null;
                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                                vaiTro=document.toObject(VaiTro.class);
                                                                break;
                                                            }
                                                            switch (vaiTro.getMoTa()){
                                                                case CUSTOMER_ROLE:
                                                                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                                                    intent.putExtra("taiKhoanId", finalTaiKhoan.getTaiKhoanId());
                                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                    startActivity(intent);
                                                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                                                    break;
//                                                                case SHIPPINGSTAFF_ROLE:
//                                                                    break;
                                                                case ADMIN_ROLE:
                                                                    Intent adminIntent=new Intent(LoginActivity.this, AdminMainActivity.class);
                                                                    adminIntent.putExtra("taiKhoanId", finalTaiKhoan.getTaiKhoanId());
                                                                    adminIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                    startActivity(adminIntent);
                                                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                                                    break;
                                                                case SELLINGSTAFF_ROLE:
                                                                    Intent sellingStaff=new Intent(LoginActivity.this, com.foodorderingapp.activity.sellingstaff.StaffActivity.class);

                                                                    startActivity(sellingStaff);
                                                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                });
                                    }
                                }
                            }
                        });
            }
        });
    }
    FirebaseUser firebaseUser;
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser !=null)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (getIntent().getExtras() != null) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtras(getIntent().getExtras());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
