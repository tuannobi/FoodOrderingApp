package com.foodorderingapp.activity.client.mefragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class UpdateThongTin extends AppCompatActivity
      {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String taiKhoanId;
          Date date=null;
          KhachHang khachHang = null;
          EditText Editngsinh;
          EditText Editho;
          EditText Editten;
          EditText Editemail;
          EditText Editdchi;
          EditText Editsdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_khach_hang);
//        this.taiKhoanId="Fwv2eR7sb73VI4Ck0zhu";
        this.taiKhoanId = (String) getIntent().getSerializableExtra("taiKhoanId");
        System.out.println("Mã tài khoản của khách hàng vừa đăng nhập là: " + this.taiKhoanId);




        db.collection("KhachHang")
                .whereEqualTo("taiKhoanId", taiKhoanId)
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



                        Editngsinh = (EditText) findViewById(R.id.editngsinh);
                        Editho = (EditText) findViewById(R.id.editho);
                        Editten = (EditText) findViewById(R.id.editten);
                        Editemail = (EditText) findViewById(R.id.editemail);
                        Editsdt = (EditText) findViewById(R.id.editsdt);
                        Editdchi = (EditText) findViewById(R.id.editdchi);

                        //Hiện dữ liệu

                        System.out.println(khachHang.toString());
                        Editho.setText(khachHang.getHo());
                        Editten.setText(khachHang.getTen());
                        Editemail.setText(khachHang.getEmail());
                        Editsdt.setText(khachHang.getSDT());
                        Editdchi.setText(khachHang.getDiaChi());
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
                        Editngsinh.setText(simpleDateFormat.format(khachHang.getNgaySinh()));

                        // xử lý nút lưu
                        Button submit=(Button) findViewById(R.id.submit);
                        submit.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
//                                                          System.out.println(khachHang);
                                                         KhachHang newKhachHang=new KhachHang();
                                                         newKhachHang.setEmail(Editemail.getText().toString());
                                                         newKhachHang.setHo(Editho.getText().toString());
                                                         newKhachHang.setTen(Editten.getText().toString());
                                                         newKhachHang.setDiaChi(Editdchi.getText().toString());
                                                         newKhachHang.setSDT(Editsdt.getText().toString());
                                                         newKhachHang.setTaiKhoanId(khachHang.getTaiKhoanId());
                                                         newKhachHang.setDoanhThu(khachHang.getDoanhThu());
                                                          SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
                                                          try {
                                                              newKhachHang.setNgaySinh(simpleDateFormat.parse(Editngsinh.getText().toString()));
                                                          } catch (ParseException ex) {
                                                              ex.printStackTrace();
                                                          }
                                                          System.out.println(newKhachHang);
                                                          db.collection("KhachHang").document(khachHang.getTaiKhoanId()).set(newKhachHang);
                                                          finish();

                                                      }
                                                  }

                        );
                    }
                });
        //xử lý nút date
        Button btn_date=(Button) findViewById(R.id.btn_date);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    show_Datepicker();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        //xử lý nút Thay đổi mật khẩu
        Button btn_pass=(Button) findViewById(R.id.btn_pass);
        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pass = new Intent(UpdateThongTin.this, UpdatePass.class);
                pass.putExtra("taiKhoanId", taiKhoanId);
                System.out.println("ID ở MeFragment: "+taiKhoanId);
                startActivity(pass);
            }
        });

    }

//xử lý ngày

          private int mYear;
          private int mMonth;
          private int mDay;
          private int mHour;
          private int mMinute;
          private Calendar c;
          private Context ctx = this;

          private void show_Datepicker() throws ParseException {
              c = Calendar.getInstance();
              int mYearParam = mYear;
              int mMonthParam = mMonth-1;
              int mDayParam = mDay;

              DatePickerDialog datePickerDialog = new DatePickerDialog(ctx,
                      new DatePickerDialog.OnDateSetListener() {

                          @Override
                          public void onDateSet(DatePicker view, int year,
                                                int monthOfYear, int dayOfMonth) {
                              mMonth = monthOfYear + 1;
                              mYear=year;
                              mDay=dayOfMonth;
                              SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
                              String dateString=mMonth+"/"+mDay+"/"+mYear;
                              System.out.println(dateString);
                              try {
                                  date=simpleDateFormat.parse(dateString);

                                  Editngsinh.setText(dateString);
                                  //Lấy date ra xử lý
                              } catch (ParseException e) {
                                  e.printStackTrace();
                              }
                          }
                      }, mYearParam, mMonthParam, mDayParam);

              datePickerDialog.show();
          }


}
