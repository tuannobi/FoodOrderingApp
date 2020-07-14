package com.foodorderingapp.activity.client.myordersfragment.donhanghuyfragment;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonHangHuyFragment extends Fragment {
    private ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String taiKhoanId;
    public DonHangHuyFragment(String taiKhoanId) {
        this.taiKhoanId=taiKhoanId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.donhanghuy_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.huyListView);
        //
        db.collection("HoaDon")
                .whereIn("trangThai",new ArrayList<>(Arrays.asList("Đơn hàng hủy")))
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
                                System.out.println("Số hóa đơn hủy"+hoaDons.size());
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

    class MyAdapter extends ArrayAdapter<HoaDon> {
        Context context;
        List<HoaDon> hoaDons;
        MyAdapter(Context context,List<HoaDon> hoaDons){
            super(context,R.layout.orders_cancel_row,hoaDons);
            this.context=context;
            this.hoaDons=hoaDons;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.orders_cancel_row,parent,false);
            TextView dayTextView=row.findViewById(R.id.dayHuy);
            TextView monthTextView=row.findViewById(R.id.monthHuy);
            TextView yearTextView=row.findViewById(R.id.yearHuy);
            TextView orderIdTextView=row.findViewById(R.id.orderidHuy);
            TextView totalTextView=row.findViewById(R.id.totalmoneyHuy);
            Button button=row.findViewById(R.id.detailbuttonHuy);
            dayTextView.setText((String) DateFormat.format("dd",hoaDons.get(position).getThoiGianHuy()));
            monthTextView.setText((String) DateFormat.format("MM",hoaDons.get(position).getThoiGianHuy()));
            yearTextView.setText((String) DateFormat.format("yyyy",hoaDons.get(position).getThoiGianHuy()));
            orderIdTextView.setText("Order#"+hoaDons.get(position).getHoaDonId());
            totalTextView.setText("Tổng tiền: "+hoaDons.get(position).getTongTienThanhToan());
            return row;
        }
    }
}
