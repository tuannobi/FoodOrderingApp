package com.foodorderingapp.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhuyenMai;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.ls.LSOutput;

import java.util.Date;

public class DialogCoupon extends DialogFragment {
    float phanTramGiam;
    String hoaDonId;
    EditText cou;
    Button huy,apdung;
    float tienkhuyenmai;
    float tongtien;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static DialogCoupon newInstance() {
        DialogCoupon dialogItem = new DialogCoupon();
//        SanPham sanPham = new SanPham(null,"a",1,2,3,"Có","Coffe","https://firebasestorage.googleapis.com/v0/b/foodorderingapp-85e14.appspot.com/o/images%2F5c8a4233-c294-4bb8-bd50-f32736f42f01?alt=media&token=0f6e79aa-d821-4afe-8f06-451a9b9b27c1","abc");
//        Bundle args = new Bundle();
//        args.putParcelable("sanpham", (Parcelable) sanPham);
//        dialogItem.setArguments(args);
        return dialogItem;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khuyenmai_dialog, container);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        apdung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("KhuyenMai")
                        .document(cou.getText().toString())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                KhuyenMai khuyenMai = document.toObject(KhuyenMai.class);
                                Date d = new Date();
                                System.out.println(d);
                                System.out.println(khuyenMai.getNgayHetHan());
                                if(d.compareTo(khuyenMai.getNgayHetHan())>0){
                                    Toast.makeText(getActivity(),"Mã khuyến mãi đã hết hạn!",Toast.LENGTH_SHORT).show();
                                    cou.setText("");
                                }
                                else {
                                    phanTramGiam = khuyenMai.getPhantramgiam();
                                    System.out.println(phanTramGiam);
                                    db.collection("HoaDon")
                                            .document(hoaDonId)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        if (document.exists()) {
                                                            System.out.println(hoaDonId);
                                                            HoaDon h = document.toObject(HoaDon.class);
                                                            tongtien = h.getTongTienThanhToan()+h.getTienKhuyenMai();
                                                            tienkhuyenmai = tongtien*phanTramGiam;
                                                            db.collection("HoaDon")
                                                                    .document(hoaDonId)
                                                                    .update("tienKhuyenMai",tienkhuyenmai,"tongTienThanhToan",tongtien-tienkhuyenmai);
                                                            dismiss();
                                                            Toast.makeText(getActivity(),"Áp dụng mã khuyễn mãi thành công!",Toast.LENGTH_LONG).show();
                                                            System.out.println(hoaDonId);
                                                        }
                                                    }
                                                }
                                            });
                                }


//                                db.collection("HoaDon")
//                                        .document(hoaDonId)
//                                        .update("tienKhuyenMai",)
                            } else {
                                cou.setText("");
                                Toast.makeText(getActivity(),"Mã khuyến mãi không tồn tại!",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            System.out.println(task.getException());
                        }
                    }
                });

            }
        });
    }
    private void AnhXa(View view) {
        cou = view.findViewById(R.id.cou);
        huy = view.findViewById(R.id.huy);
        apdung = view.findViewById(R.id.apdung);
        hoaDonId = getArguments().getString("hoaDonId");
    }
}
