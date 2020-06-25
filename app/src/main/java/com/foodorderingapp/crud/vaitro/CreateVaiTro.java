package com.foodorderingapp.crud.vaitro;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;
import com.foodorderingapp.model.VaiTro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateVaiTro extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<VaiTro> vaiTroList=new ArrayList<>(Arrays.asList(
                new VaiTro(null,"Khách hàng")
                ,new VaiTro(null, "Nhân viên bán hàng")
                ,new VaiTro(null, "Nhân viên giao hàng"),
                new VaiTro(null, "Admin")));
        for(VaiTro vaiTro:vaiTroList){
            db.collection("VaiTro")
                    .add(vaiTro)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.update("vaiTroId",documentReference.getId());
                            Log.d("TAG","ID= "+documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG","Error",e);
                        }
                    });
        }
    }
}
