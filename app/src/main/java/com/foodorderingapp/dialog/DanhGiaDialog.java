package com.foodorderingapp.dialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class DanhGiaDialog extends AppCompatDialogFragment {

    private TextView nhanXet;
    private RatingBar ratingBar;
    private Button button;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static String sanPhamId;
    private static String taiKhoanId;

    public DanhGiaDialog(String title, String sanPhamId, String taiKhoanId) {
        this.sanPhamId=sanPhamId;
        this.taiKhoanId=taiKhoanId;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.review_chitiethoadon, container);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nhanXet=view.findViewById(R.id.getRatingg);
        ratingBar=view.findViewById(R.id.rating_star);
        button=view.findViewById(R.id.nhanxetbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DanhGia danhGia=new DanhGia();
                danhGia.setHangMuc(Integer.parseInt(String.valueOf(ratingBar.getNumStars())));
                danhGia.setNhanXet(nhanXet.getText().toString());
                danhGia.setTaiKhoanId(taiKhoanId);
                danhGia.setSanPhamId(sanPhamId);
                System.out.println("Clicked");
                System.out.println(danhGia.toString());
                db.collection("DanhGia")
                        .add(danhGia)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
                                danhGia.setDanhGiaId(documentReference.getId());
                                db.collection("DanhGia").document(documentReference.getId()).set(danhGia);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });

                Toast.makeText(getContext(),"Gửi đánh giá thành công",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

}
