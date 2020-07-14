package com.foodorderingapp.activity.client.myordersfragment.lichsudonhangfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.MainActivity;
import com.foodorderingapp.activity.client.myordersfragment.donhanghuyfragment.DonHangHuyFragment;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class LichSuDonHangFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView listView;
    private String taiKhoanId;
    private Button button;

    public LichSuDonHangFragment(String taiKhoanId) {
        this.taiKhoanId=taiKhoanId;
    }

    public LichSuDonHangFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lichsudonhang_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.lichSuListView);
        //Get data from firebase
        //
        db.collection("HoaDon")
                .whereIn("trangThai",new ArrayList<>(Arrays.asList("Giao thành công")))
                .whereEqualTo("taiKhoanId",taiKhoanId)
                .addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e!=null){
                            return;
                        }
                        List<HoaDon> hoaDons = new ArrayList<>();
                        if (queryDocumentSnapshots != null) {
                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                    hoaDons.add(doc.toObject(HoaDon.class));
                                }
                                System.out.println("Số hóa đã giao"+hoaDons.size());
                                if(hoaDons!=null){
                                    MyAdapter myAdapter=new MyAdapter(getContext(),hoaDons);
                                    listView.setAdapter(myAdapter);
                                }
                            }



                        } else{
                            Log.d("TAG", "Current data: null");
                        }

                    }
                });
        //


    }


    class MyAdapter extends ArrayAdapter<HoaDon>{
        Context context;
        List<HoaDon> hoaDons;
        MyAdapter(Context context,List<HoaDon> hoaDons){
            super(context,R.layout.orders_row,hoaDons);
            this.context=context;
            this.hoaDons=hoaDons;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.history_row,parent,false);
            TextView dayTextView=row.findViewById(R.id.dayLichSu);
            TextView monthTextView=row.findViewById(R.id.monthLichSu);
            TextView yearTextView=row.findViewById(R.id.yearLichSu);
            TextView orderIdTextView=row.findViewById(R.id.orderidLichSu);
            TextView totalTextView=row.findViewById(R.id.totalmoneyLichSu);
            Button button=row.findViewById(R.id.detailbuttonLichSu);
            System.out.println(hoaDons.get(position).getThoiGianGiaoHangThanhCong().toString());
            dayTextView.setText((String) DateFormat.format("dd",hoaDons.get(position).getThoiGianGiaoHangThanhCong()));
            monthTextView.setText((String) DateFormat.format("MM",hoaDons.get(position).getThoiGianGiaoHangThanhCong()));
            yearTextView.setText((String) DateFormat.format("yyyy",hoaDons.get(position).getThoiGianGiaoHangThanhCong()));
            orderIdTextView.setText("Order#"+hoaDons.get(position).getHoaDonId());
            totalTextView.setText("Tổng tiền: "+hoaDons.get(position).getTongTienThanhToan());
            //

//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(getContext(), ChiTietDonHangActivity.class);
//                    intent.putExtra("khachHangId", hoaDons.get(position).getKhachHangId());
//                    intent.putExtra("hoaDonId", hoaDons.get(position).getHoaDonId());
//                    startActivity(intent);
//                }
//            });
            //
            return row;
        }
    }
}
