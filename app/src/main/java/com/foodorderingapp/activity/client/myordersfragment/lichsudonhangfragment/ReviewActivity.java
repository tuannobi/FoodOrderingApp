package com.foodorderingapp.activity.client.myordersfragment.lichsudonhangfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.foodorderingapp.R;
import com.foodorderingapp.model.DanhGia;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReviewActivity extends AppCompatActivity {

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private ImageButton imageButton5;
    private EditText editText;
    private boolean clickedStar[]={false,false,false,false,false};
    private Button button;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        imageButton1=findViewById(R.id.star1);
        imageButton2=findViewById(R.id.star2);
        imageButton3=findViewById(R.id.star3);
        imageButton4=findViewById(R.id.star4);
        imageButton5=findViewById(R.id.star5);
        button=findViewById(R.id.okbutton);
        editText=findViewById(R.id.comment);
        starbuttonEvent();
        saveReview();
    }

    public void saveReview(){
        button=findViewById(R.id.okbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hangMuc=0;
                editText= findViewById(R.id.comment);
                if(clickedStar[0]){
                    hangMuc=1;
                }else if(clickedStar[1]){
                    hangMuc=2;
                }else if(clickedStar[2]){
                    hangMuc=3;
                }else if(clickedStar[3]){
                    hangMuc=4;
                }else if(clickedStar[4]){
                    hangMuc=5;
                }
                final String khachHangId= getIntent().getSerializableExtra("khachHangId").toString();
                final String hoaDonId= getIntent().getSerializableExtra("hoaDonId").toString();
                DanhGia danhGia=new DanhGia();
                danhGia.setHangMuc(hangMuc);
                danhGia.setHoaDonId(hoaDonId);
                danhGia.setKhachHangId(khachHangId);
                danhGia.setNhanXet(editText.getText().toString());

                    //
                db.collection("DanhGia")
                        .add(danhGia)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                documentReference.update("danhGiaId",documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });

                DocumentReference documentReference = db.collection("HoaDon").document(hoaDonId);
                documentReference
                        .update("danhGia", 1)
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
        });
        finish();
        Toast.makeText(getApplicationContext(),"Sent Successfully!",Toast.LENGTH_SHORT);
    }

    public void starbuttonEvent(){
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedStar[0]){
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=false;
                    clickedStar[1]=false;
                    clickedStar[2]=false;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }else {
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=false;
                    clickedStar[2]=false;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedStar[1]){
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=false;
                    clickedStar[2]=false;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }else {
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=false;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedStar[2]){
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=false;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }else {
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=true;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedStar[3]){
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=true;
                    clickedStar[3]=false;
                    clickedStar[4]=false;
                }else {
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=true;
                    clickedStar[3]=true;
                    clickedStar[4]=false;
                }
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedStar[4]){
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.empty_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=true;
                    clickedStar[3]=true;
                    clickedStar[4]=false;
                }else {
                    imageButton1.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton2.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton3.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton4.setBackground(getResources().getDrawable(R.drawable.full_star));
                    imageButton5.setBackground(getResources().getDrawable(R.drawable.full_star));
                    clickedStar[0]=true;
                    clickedStar[1]=true;
                    clickedStar[2]=true;
                    clickedStar[3]=true;
                    clickedStar[4]=true;
                }
            }
        });
    }

}
