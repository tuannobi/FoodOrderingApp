package com.foodorderingapp.activity.admin.customerfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.foodorderingapp.activity.client.mefragment.UpdateThongTin;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


public class CustomerFragment extends Fragment {
    private ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_fragment,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listkhachhang);
              db.collection("KhachHang")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }

                        List<KhachHang> khachHangs = new ArrayList<>();
                            for (QueryDocumentSnapshot doc : value) {
                                khachHangs.add(doc.toObject(KhachHang.class));
                            }
                        MyAdapter myAdapter = new MyAdapter(getContext(), khachHangs);
                            System.out.println(khachHangs.toString());
                            listView.setAdapter(myAdapter);
                        }

                    });



    }


    class MyAdapter extends ArrayAdapter<KhachHang> {
        Context context;
        List<KhachHang> khachHangs;


        MyAdapter(Context context, List<KhachHang> khachHangs) {
            super(context,R.layout.customrow_admin, khachHangs);
            this.context = context;
            this.khachHangs = khachHangs;

        }
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.customrow_admin,parent,false);


            TextView hokhachhang = row.findViewById(R.id.hokhachhang);
            TextView doanhthukhachhang = row.findViewById(R.id.doanhthu);
            Button buttonchitiet = row.findViewById(R.id.chitietkhachhang);


            hokhachhang.setText("Tên khách hàng:   " + khachHangs.get(position).getHo()+" "+khachHangs.get(position).getTen());

            doanhthukhachhang.setText("Doanh thu: " + khachHangs.get(position).getDoanhThu());

            final String emailkhachhang=khachHangs.get(position).getEmail();
            buttonchitiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iSubchitiet = new Intent(getContext(), Custom_detail.class);
                    iSubchitiet.putExtra("email", emailkhachhang);
                    System.out.println("email ở CustomFragment: "+emailkhachhang);

                    startActivity(iSubchitiet);

                }});

            return row;
        }



    }
}
