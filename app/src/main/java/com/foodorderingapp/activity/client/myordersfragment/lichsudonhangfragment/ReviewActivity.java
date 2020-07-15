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
        setContentView(R.layout.review_chitiethoadon);


                            button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DanhGia danhGia=new DanhGia();
                            db.collection("DanhGia")
                                    .add(danhGia)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("TAG", "Error adding document", e);
                                        }
                                    });
                            Toast.makeText(getApplicationContext()
                                    ,"Gửi đánh giá thành công",Toast.LENGTH_SHORT).show();
                        }
                    });
    }

}
