//package com.foodorderingapp.activity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.foodorderingapp.R;
//import com.foodorderingapp.activity.admin.AdminMainActivity;
//import com.foodorderingapp.activity.client.MainActivity;
//import com.foodorderingapp.activity.staff.StaffActivity;
//import com.foodorderingapp.model.TaiKhoan;
//import com.foodorderingapp.model.VaiTro;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//public class LoginActivity extends AppCompatActivity {
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private EditText userNameEditText;
//    private EditText passwordEditText;
//    private Button loginButton;
//    private static final String CUSTOMER_ROLE="Khách hàng";
//    private static final String STAFF_ROLE="Nhân viên";
//    private static final String ADMIN_ROLE="Admin";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        userNameEditText=findViewById(R.id.usernameEditText);
//        passwordEditText=findViewById(R.id.passwordEditText);
//        loginButton=findViewById(R.id.loginButton);
//        loginButtonEvent();
//    }
//
//    private void loginButtonEvent(){
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db.collection("TaiKhoan")
//                        .whereEqualTo("userName",userNameEditText.getText().toString())
//                        .whereEqualTo("password",passwordEditText.getText().toString())
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if(task.isSuccessful()){
//                                    TaiKhoan taiKhoan=null;
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        taiKhoan=document.toObject(TaiKhoan.class);
//                                        break;
//                                    }
//                                    if(taiKhoan==null){
//                                        Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
//                                    }else{
//                                        //Kiểm tra vai trò và mở các activity tương ứng
//                                        final TaiKhoan finalTaiKhoan = taiKhoan;
//                                        db.collection("VaiTro")
//                                                .whereEqualTo("vaiTroId",taiKhoan.getVaiTroId())
//                                                .get()
//                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                                        if(task.isSuccessful()){
//                                                            VaiTro vaiTro=null;
//                                                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                                                vaiTro=document.toObject(VaiTro.class);
//                                                                break;
//                                                            }
//                                                            switch (vaiTro.getMoTa()){
//                                                                case CUSTOMER_ROLE:
//                                                                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
//                                                                    intent.putExtra("taiKhoanId", finalTaiKhoan.getTaiKhoanId());
//                                                                    startActivity(intent);
//                                                                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                                                                    break;
////                                                                case SHIPPINGSTAFF_ROLE:
////                                                                    break;
//                                                                case ADMIN_ROLE:
//                                                                    Intent adminIntent=new Intent(getApplicationContext(), AdminMainActivity.class);
//                                                                    adminIntent.putExtra("taiKhoanId", finalTaiKhoan.getTaiKhoanId());
//                                                                    startActivity(adminIntent);
//                                                                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                                                                    break;
//                                                                case STAFF_ROLE:
//                                                                    Intent sellingStaff=new Intent(getApplicationContext(), StaffActivity.class);
//                                                                    startActivity(sellingStaff);
//                                                                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                                                                    break;
//                                                            }
//                                                        }
//                                                    }
//                                                });
//                                    }
//                                }
//                            }
//                        });
//            }
//        });
//    }
//
//}
