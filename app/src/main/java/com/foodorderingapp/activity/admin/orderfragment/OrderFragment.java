package com.foodorderingapp.activity.admin.orderfragment;

import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
import java.util.List;

public class OrderFragment extends Fragment {
    private ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listhoadon);

        db.collection("HoaDon")
                .whereEqualTo("trangThai", "Chờ giao hàng")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }

                        final List<HoaDon> hoaDons = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            hoaDons.add(doc.toObject(HoaDon.class));
                        }

//                        for(HoaDon item : hoaDons)
//                        {
//                            db.collection("KhachHang")
//                                    .whereEqualTo("taiKhoanId",item.getTaiKhoanId())
//                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                                        @Override
//                                        public void onEvent(@Nullable QuerySnapshot value,
//                                                            @Nullable FirebaseFirestoreException e) {
//                                            if (e != null) {
//                                                Log.w("TAG", "Listen failed.", e);
//                                                return;
//                                            }
//
//                                            List<KhachHang> khachHangs = new ArrayList<>();
//                                            for (QueryDocumentSnapshot doc : value) {
//                                                khachHangs.add(doc.toObject(KhachHang.class));
//                                                break;
//                                            }
//                                            MyAdapterKhachHang myAdapterKhachHang=new MyAdapterKhachHang(getActivity(),khachHangs);
//                                            System.out.println(khachHangs.toString());
//                                            listView.setAdapter(myAdapterKhachHang);
//
//
//                                        }
//                                    });
//                        }

                        MyAdapter myAdapter=new MyAdapter(getActivity(),hoaDons);
                        System.out.println(hoaDons.toString());
                        listView.setAdapter(myAdapter);



                    }
                });



    }


    class MyAdapter extends ArrayAdapter<HoaDon> {
        Context context;
        List<HoaDon> hoaDons;


        MyAdapter(Context context, List<HoaDon> hoaDons) {
            super(context,R.layout.oderrow_admin, hoaDons);
            this.context = context;
            this.hoaDons = hoaDons;

        }
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.oderrow_admin,parent,false);
            TextView orderidtext=row.findViewById(R.id.orderid);

            TextView totalTextView=row.findViewById(R.id.totalmoney);
            Button button=row.findViewById(R.id.capnhatbuton);

            orderidtext.setText("Order#"+hoaDons.get(position).getHoaDonId());
            totalTextView.setText("Tổng tiền "+hoaDons.get(position).getTongTienThanhToan());
            button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              HoaDon hoaDonss=new HoaDon();

                                              Date today = new Date();
                                              System.out.println(today);
                                              hoaDonss.setChiTietHoaDon(hoaDons.get(position).getChiTietHoaDon());
                                              hoaDonss.setNgayTao(hoaDons.get(position).getNgayTao());
                                              hoaDonss.setTaiKhoanId(hoaDons.get(position).getTaiKhoanId());
                                              hoaDonss.setMaKhuyenMai(hoaDons.get(position).getMaKhuyenMai());
                                              hoaDonss.setPhiShip(hoaDons.get(position).getPhiShip());
                                              hoaDonss.setQuan(hoaDons.get(position).getQuan());
                                              hoaDonss.setSoNha(hoaDons.get(position).getSoNha());
                                              hoaDonss.setNgayGiao(hoaDons.get(position).getNgayGiao());
                                              hoaDonss.setThoiGianDatHang(hoaDons.get(position).getThoiGianDatHang());
                                              hoaDonss.setTienKhuyenMai(hoaDons.get(position).getTienKhuyenMai());
                                              hoaDonss.setThoiGianHuy(hoaDons.get(position).getThoiGianHuy());
                                              hoaDonss.setHoaDonId(hoaDons.get(position).getHoaDonId());

                                              hoaDonss.setTongTienThanhToan(hoaDons.get(position).getTongTienThanhToan());
                                              hoaDonss.setThoiGianGiaoHangThanhCong(today);
                                              hoaDonss.setTrangThai("Giao thành công");
                                              db.collection("HoaDon").document(hoaDons.get(position).getHoaDonId()).set(hoaDonss);

                                          }});

            return row;
        }



    }


}
