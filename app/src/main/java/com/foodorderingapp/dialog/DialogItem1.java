package com.foodorderingapp.dialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.ChiTietHoaDon;

import com.foodorderingapp.model.HoaDon;

import com.foodorderingapp.model.SanPham;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DialogItem1 extends DialogFragment {
    TextView ten;
    TextView gia;
    int soluong;
    float tongtien;
    float giaca;
    Button mua;
    ImageButton add, subtract;
    TextView sl;
    ChiTietHoaDon chitiethoadon;
    String taiKhoanId;
    String hoaDonId;
    int position;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference hoadonRef = db.collection("HoaDon");
    public static DialogItem1 newInstance() {
        DialogItem1 dialogItem = new DialogItem1();
//        SanPham sanPham = new SanPham(null,"a",1,2,3,"Có","Coffe","https://firebasestorage.googleapis.com/v0/b/foodorderingapp-85e14.appspot.com/o/images%2F5c8a4233-c294-4bb8-bd50-f32736f42f01?alt=media&token=0f6e79aa-d821-4afe-8f06-451a9b9b27c1","abc");
//        Bundle args = new Bundle();
//        args.putParcelable("sanpham", (Parcelable) sanPham);
//        dialogItem.setArguments(args);
        return dialogItem;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_item1, container);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);

        ten.setText(chitiethoadon.getTenSanPham());
        gia.setText(giaca+"");
        sl.setText(soluong+"");
        mua.setText(tongtien+"");
        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mua.getText().toString().equals("Hủy sản phẩm")) {
                    db.collection("HoaDon")
                            .document(hoaDonId)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot snapshot) {
                                    List<ChiTietHoaDon> chitiet = snapshot.toObject(HoaDon.class).getChiTietHoaDon();
                                    chitiet.remove(position);
                                    float tongtien1 =0;
                                    for (int i= 0;i<chitiet.size();i++){
                                        tongtien1 += chitiet.get(i).getTongTien();
                                    }
                                    tongtien1 += snapshot.toObject(HoaDon.class).getPhiShip();
                                    db.collection("HoaDon")
                                            .document(hoaDonId)
                                            .update("chiTietHoaDon", chitiet,"tongTienThanhToan",tongtien1)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    System.out.println("Thành công");
                                                }
                                            });

                                }
                            });
                    dismiss();
                } else {
                    db.collection("HoaDon")
                            .document(hoaDonId)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot snapshot) {
                                    ChiTietHoaDon chinhsua = new ChiTietHoaDon(chitiethoadon.getSanPhamId(), chitiethoadon.getTenSanPham(), chitiethoadon.getGia(), Integer.parseInt(sl.getText().toString()), Float.parseFloat(mua.getText().toString()),false);
                                    List<ChiTietHoaDon> chitiet = snapshot.toObject(HoaDon.class).getChiTietHoaDon();
                                    chitiet.set(position, chinhsua);
                                    float tongtien1 =0;
                                    for (int i= 0;i<chitiet.size();i++){
                                        tongtien1 += chitiet.get(i).getTongTien();
                                    }
                                    tongtien1 += snapshot.toObject(HoaDon.class).getPhiShip();
                                    db.collection("HoaDon")
                                            .document(hoaDonId)
                                            .update("chiTietHoaDon", chitiet,"tongTienThanhToan",tongtien1)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    System.out.println("Thành công");
                                                }
                                            });
                                }
                            });
                    dismiss();
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bamNutAdd();
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bamNutSubtract();
            }
        });



        //Điểm dừng
    }
    public void bamNutAdd(){
        soluong+=1;
        sl.setText(soluong+"");
        tongtien = soluong * giaca;
        mua.setText(tongtien+"");
    }

    public void bamNutSubtract(){
        if ((Integer.parseInt((String) sl.getText())==2)) {
            soluong = 1;
            sl.setText(soluong+"");
            mua.setText(giaca+"");
        } else if((Integer.parseInt((String) sl.getText())==1)) {
            System.out.println(sl.getText());
            soluong = 0;
            sl.setText(0+"");
            mua.setText("Hủy sản phẩm");
        } else if ((Integer.parseInt((String) sl.getText())==0)){
            soluong = 0;
            mua.setText("Hủy sản phẩm");
        }
        else {
            soluong -=1;
            sl.setText(soluong+"");
            tongtien = soluong * giaca;
            mua.setText(tongtien+"");
        }
    }
    public void AnhXa(View view) {
        chitiethoadon = (ChiTietHoaDon) getArguments().getSerializable("chitiethoadonn");
        giaca = chitiethoadon.getGia();
        soluong = chitiethoadon.getSoLuong();
        tongtien  = chitiethoadon.getTongTien();
        taiKhoanId = getArguments().getString("taiKhoanId");
        hoaDonId = getArguments().getString("hoaDonId");
        position = getArguments().getInt("position");
        ten = (TextView)view.findViewById(R.id.tensp);
        gia = (TextView)view.findViewById(R.id.gia);
        mua = (Button)view.findViewById(R.id.mua);
        add = (ImageButton)view.findViewById(R.id.addd);
        subtract = (ImageButton)view.findViewById(R.id.subtractt);
        sl  = (TextView)view.findViewById(R.id.soluong);
    }

}
