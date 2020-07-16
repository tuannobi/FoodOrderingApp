package com.foodorderingapp.activity.admin.promotionfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.foodorderingapp.R;
import com.foodorderingapp.model.ChiTietKhuyenMai;
import com.foodorderingapp.model.KhuyenMai;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChiTietKhuyenMaiActivity extends AppCompatActivity {

    private EditText ngayBatDau;
    private EditText ngayKetThuc;
    private EditText phanTramGiam;
    private Button button;
    final Calendar myCalendar = Calendar.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khuyen_mai);

        ngayBatDau=findViewById(R.id.khuyenmai_ngaybatdau);
        ngayKetThuc=findViewById(R.id.khuyenmai_ngayketthuc);
        phanTramGiam=findViewById(R.id.khuyenmai_phantramgiam);
        button=findViewById(R.id.khuyenmai_luu);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateNgayBatDau();
            }

        };

        ngayBatDau.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ChiTietKhuyenMaiActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateNgayKetThuc();
            }

        };

        ngayKetThuc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ChiTietKhuyenMaiActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhuyenMai khuyenMai=new KhuyenMai();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                String ngayBatDauTemp=ngayBatDau.getText().toString();
                String ngayKetThucTemp=ngayKetThuc.getText().toString();
                try {
                    khuyenMai.setNgayTao(formatter.parse(String.valueOf(ngayBatDauTemp)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    khuyenMai.setNgayHetHan(formatter.parse(String.valueOf(ngayKetThucTemp)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                khuyenMai.setPhantramgiam(Float.parseFloat(phanTramGiam.getText().toString()));

                db.collection("KhuyenMai")
                        .add(khuyenMai)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
                                DocumentReference washingtonRef = db.collection("KhuyenMai").document(documentReference.getId());

// Set the "isCapital" field of the city 'DC'
                                washingtonRef
                                        .update("khuyenMaiId", documentReference.getId())
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
        });
    }

    private void updateNgayBatDau() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ngayBatDau.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateNgayKetThuc() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ngayKetThuc.setText(sdf.format(myCalendar.getTime()));
    }
}
