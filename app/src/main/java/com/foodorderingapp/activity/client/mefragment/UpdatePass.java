package com.foodorderingapp.activity.client.mefragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodorderingapp.R;
import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UpdatePass extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String taiKhoanId;
    TaiKhoan taiKhoan=null;
    EditText Editpasscu;
    EditText Editpassmoi;
    TextView Error;
    Button Luu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass);
        this.taiKhoanId = (String) getIntent().getSerializableExtra("taiKhoanId");
        System.out.println("Mã tài khoản của khách hàng update pass là: " + this.taiKhoanId);

        Editpasscu=(EditText) findViewById(R.id.editpasscu);
        Editpassmoi=(EditText) findViewById(R.id.editpassmoi);
        Error=(TextView) findViewById(R.id.error);
        Luu=(Button) findViewById(R.id.btnluu);
        LuuButtonEvent();
    }

    private void LuuButtonEvent() {
        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("TaiKhoan")
                        .whereEqualTo("taiKhoanId", taiKhoanId)
                        .whereEqualTo("password", Editpasscu.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        taiKhoan = document.toObject(TaiKhoan.class);
                                        break;
                                    }
                                    if (taiKhoan != null) {
                                        TaiKhoan newtaikhoan = new TaiKhoan();
                                        newtaikhoan.setPassword(Editpassmoi.getText().toString());
                                        newtaikhoan.setUserName(taiKhoan.getUserName());
                                        newtaikhoan.setTaiKhoanId(taiKhoan.getTaiKhoanId());
                                        newtaikhoan.setKichHoat(taiKhoan.getKichHoat());
                                        newtaikhoan.setNgayTao(taiKhoan.getNgayTao());
                                        newtaikhoan.setVaiTroId(taiKhoan.getVaiTroId());
                                        db.collection("TaiKhoan").document(taiKhoan.getTaiKhoanId()).set(newtaikhoan);
                                        Toast.makeText(getApplicationContext(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Mật khẩu sai",Toast.LENGTH_SHORT).show();
                                        Editpasscu.setText("");
                                        Editpassmoi.setText("");
                                        return;
                                    }
                                }
                            }


                        });
            }
        });

    }

}