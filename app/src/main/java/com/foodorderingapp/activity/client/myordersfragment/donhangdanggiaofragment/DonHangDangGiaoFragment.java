package com.foodorderingapp.activity.client.myordersfragment.donhangdanggiaofragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.foodorderingapp.R;
import com.foodorderingapp.model.HoaDon;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DonHangDangGiaoFragment extends Fragment {
    private ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String taiKhoanId;

    public DonHangDangGiaoFragment(String taiKhoanId) {
        this.taiKhoanId=taiKhoanId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.donhangdanggiao_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.dangGiaoListView);
        //
        db.collection("HoaDon")
                .whereIn("trangThai",new ArrayList<>(Arrays.asList("Chờ giao hàng")))
                .whereEqualTo("taiKhoanId",taiKhoanId)
                .orderBy("thoiGianDatHang", Query.Direction.DESCENDING)
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
                                System.out.println("Số lượng đơn hàng đang giao: "+hoaDons.size());
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
            super(context,R.layout.orders_row,hoaDons);
            this.context=context;
            this.hoaDons=hoaDons;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.orders_row,parent,false);
            TextView dayTextView=row.findViewById(R.id.day);
            TextView monthTextView=row.findViewById(R.id.month);
            TextView yearTextView=row.findViewById(R.id.year);
            TextView orderIdTextView=row.findViewById(R.id.orderid);
            TextView totalTextView=row.findViewById(R.id.totalmoney);
            Button button=row.findViewById(R.id.detailbutton);
            System.out.println(hoaDons.get(position).getThoiGianDatHang().toString());
            dayTextView.setText((String) DateFormat.format("dd",hoaDons.get(position).getThoiGianDatHang()));
            monthTextView.setText((String) DateFormat.format("MM",hoaDons.get(position).getThoiGianDatHang()));
            yearTextView.setText((String) DateFormat.format("yyyy",hoaDons.get(position).getThoiGianDatHang()));
            orderIdTextView.setText("Order#"+hoaDons.get(position).getHoaDonId());
            totalTextView.setText("Tổng tiền: "+hoaDons.get(position).getTongTienThanhToan());
            button.setText(hoaDons.get(position).getTrangThai());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Huy don hang");
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    DocumentReference docRef = db.collection("HoaDon").document(hoaDons.get(position).getHoaDonId());
                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    HoaDon hoaDon=document.toObject(HoaDon.class);
                                                    hoaDon.setThoiGianHuy(new Date());
                                                    hoaDon.setTrangThai("Đơn hàng hủy");
                                                    db.collection("HoaDon")
                                                            .document(hoaDons.get(position).getHoaDonId())
                                                            .set(hoaDon);
                                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                    ft.detach(DonHangDangGiaoFragment.this).attach(DonHangDangGiaoFragment.this).commit();
                                                } else {
                                                    Log.d("TAG", "No such document");
                                                }
                                            } else {
                                                Log.d("TAG", "get failed with ", task.getException());
                                            }
                                        }
                                    });


                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn chắc chắn muốn hủy đơn hàng này?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            });
            return row;
        }
    }

}
