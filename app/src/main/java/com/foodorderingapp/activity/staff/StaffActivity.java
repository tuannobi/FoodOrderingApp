package com.foodorderingapp.activity.staff;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.foodorderingapp.R;
import com.foodorderingapp.model.HoaDon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffActivity extends AppCompatActivity {

    private ListView listView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellingstaff_main);

        listView=findViewById(R.id.sellingstaffList);
        searchView=findViewById(R.id.search_hoadon);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(),"change",Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        db.collection("HoaDon")
                .whereIn("trangThai",new ArrayList<>(Arrays.asList("Chờ xử lý")))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }

                        List<HoaDon> hoaDons = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            hoaDons.add(doc.toObject(HoaDon.class));
                        }
                        StaffActivity.MyAdapter myAdapter=new StaffActivity.MyAdapter(getApplicationContext(),hoaDons);
                        System.out.println(hoaDons.toString());
                        listView.setAdapter(myAdapter);
                    }
                });

    }

    class MyAdapter extends ArrayAdapter<HoaDon> {
        Context context;
        List<HoaDon> hoaDons;
        MyAdapter(Context context,List<HoaDon> hoaDons){
            super(context,R.layout.orders_row_preprocess,hoaDons);
            this.context=context;
            this.hoaDons=hoaDons;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.orders_row_preprocess,parent,false);
            TextView dayTextView=row.findViewById(R.id.day);
            TextView monthTextView=row.findViewById(R.id.month);
            TextView yearTextView=row.findViewById(R.id.year);
            TextView orderIdTextView=row.findViewById(R.id.orderid);
            TextView totalTextView=row.findViewById(R.id.totalmoney);
            Button button=row.findViewById(R.id.detailbutton);
            System.out.println(hoaDons.get(position).getThoiGianGiaoHangThanhCong().toString());
            dayTextView.setText((String) DateFormat.format("dd",hoaDons.get(position).getThoiGianDatHang()));
            monthTextView.setText((String) DateFormat.format("MM",hoaDons.get(position).getThoiGianDatHang()));
            yearTextView.setText((String) DateFormat.format("yyyy",hoaDons.get(position).getThoiGianDatHang()));
            orderIdTextView.setText("Order#"+hoaDons.get(position).getHoaDonId());
            totalTextView.setText("Tổng tiền: "+hoaDons.get(position).getTongTienThanhToan());
//            button.setText(hoaDons.get(position).getTrangThai());


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Clicked "+hoaDons.get(position).getHoaDonId(),Toast.LENGTH_SHORT).show();
                    DocumentReference washingtonRef = db.collection("HoaDon").document(hoaDons.get(position).getHoaDonId());
                    washingtonRef
                            .update("trangThai", "Chờ giao hàng")
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "Thành công");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Thất bại", e);
                                }
                            });
                }
            });

            return row;
        }
    }

}
