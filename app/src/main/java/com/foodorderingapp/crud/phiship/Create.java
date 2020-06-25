package com.foodorderingapp.crud.phiship;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.model.PhiShip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Create extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<PhiShip> phiShipList=new ArrayList<>();
        phiShipList.add(new PhiShip("Quận 1",10000));
        phiShipList.add(new PhiShip("Quận 2",10000));
        phiShipList.add(new PhiShip("Quận 3",10000));
        phiShipList.add(new PhiShip("Quận 4",10000));
        phiShipList.add(new PhiShip("Quận 5",10000));
        phiShipList.add(new PhiShip("Quận 6",10000));
        phiShipList.add(new PhiShip("Quận 7",10000));
        phiShipList.add(new PhiShip("Quận 8",10000));
        phiShipList.add(new PhiShip("Quận 9",10000));
        phiShipList.add(new PhiShip("Quận 10",10000));
        phiShipList.add(new PhiShip("Quận 11",10000));
        phiShipList.add(new PhiShip("Quận 12",10000));
        phiShipList.add(new PhiShip("Quận Bình Tân",10000));
        phiShipList.add(new PhiShip("Quận Bình Thạnh",10000));
        phiShipList.add(new PhiShip("Quận Gò vấp",10000));
        phiShipList.add(new PhiShip("Quận Phú Nhuận",10000));
        phiShipList.add(new PhiShip("Quận Tân Bình",10000));
        phiShipList.add(new PhiShip("Quận Tân Phú",10000));
        phiShipList.add(new PhiShip("Huyện Bình Chánh",20000));
        phiShipList.add(new PhiShip("Huyện Cần Giờ",20000));
        phiShipList.add(new PhiShip("Huyện Củ Chi",20000));
        phiShipList.add(new PhiShip("Huyện Hóc Môn",20000));
        phiShipList.add(new PhiShip("Huyện Nhà Bè",20000));

        //
        for(PhiShip phiShip:phiShipList){
            db.collection("PhiShip")
                    .add(phiShip)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.update("phiShipId",documentReference.getId());
                            Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        //
    }
}
