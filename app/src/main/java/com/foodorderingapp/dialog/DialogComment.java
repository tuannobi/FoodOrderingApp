package com.foodorderingapp.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.ChiTietHoaDon;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DialogComment extends DialogFragment {
    RatingBar ratingBar;
    TextView nhanxet,tenkhachhang,sodienthoaikhachang;
    DanhGia danhgia;
    Button xoabinhluan;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static DialogComment newInstance() {
        DialogComment dialogItem = new DialogComment();
//        SanPham sanPham = new SanPham(null,"a",1,2,3,"Có","Coffe","https://firebasestorage.googleapis.com/v0/b/foodorderingapp-85e14.appspot.com/o/images%2F5c8a4233-c294-4bb8-bd50-f32736f42f01?alt=media&token=0f6e79aa-d821-4afe-8f06-451a9b9b27c1","abc");
//        Bundle args = new Bundle();
//        args.putParcelable("sanpham", (Parcelable) sanPham);
//        dialogItem.setArguments(args);
        return dialogItem;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating_dialog, container);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        ratingBar.setRating(danhgia.getHangMuc());
        nhanxet.setText(danhgia.getNhanXet());
        System.out.println("đây là sanPhamId:   "+ danhgia.getSanPhamId());
        System.out.println(danhgia.getNhanXet());
        System.out.println(danhgia.getHangMuc());
        System.out.println(danhgia.getTaiKhoanId());
        db.collection("KhachHang")
                .document(danhgia.getTaiKhoanId())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        KhachHang kh = documentSnapshot.toObject(KhachHang.class);
                        tenkhachhang.setText(kh.getHo()+" "+kh.getTen());
                        sodienthoaikhachang.setText(kh.getSDT());
                    }
                });

        xoabinhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("DanhGia")
                        .document(danhgia.getDanhGiaId())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(),"Xóa bình luận thành công!",Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                        });
            }
        });
    }
    public void AnhXa(View view) {
        danhgia =  (DanhGia) getArguments().getSerializable("danhgia");
        ratingBar = (RatingBar)view.findViewById(R.id.rating1);
        nhanxet = (TextView)view.findViewById(R.id.getRating1);
        tenkhachhang = (TextView)view.findViewById(R.id.tencuakhachang);
        sodienthoaikhachang = (TextView)view.findViewById(R.id.sodienthoai);
        xoabinhluan = (Button)view.findViewById(R.id.xoabinhluan);

    }
}
