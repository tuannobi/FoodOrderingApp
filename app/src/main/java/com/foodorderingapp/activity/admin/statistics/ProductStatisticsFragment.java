package com.foodorderingapp.activity.admin.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.ChiTietHoaDon;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.SanPham;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductStatisticsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private BarChart chart;
    private Spinner spinner;
    private static List<Integer> arrays=null;
    private static  List<String> tenSanPhamArrayList=null;

    private static void addValue(Integer value){
        if(arrays==null){
            arrays=new ArrayList<>();
        }else{
            arrays.add(value);
        }
    }

    private static void addValue(String value){
        if(tenSanPhamArrayList==null){
            tenSanPhamArrayList=new ArrayList<>();
        }else{
            tenSanPhamArrayList.add(value);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_productstatic_main,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart=view.findViewById(R.id.productBarChart);
        //Lay du lieu nam hien thi len Spinner
        spinner=view.findViewById(R.id.product_spinner);
        System.out.println(Integer.parseInt(spinner.getSelectedItem().toString()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


//                db.collection("SanPham")
//                        .limit(Integer.parseInt(spinner.getSelectedItem().toString()))
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        final String sanPhamId=document.toObject(SanPham.class).getSanPhamId();
//                                        final int soLuongCu=document.toObject(SanPham.class).getSoLuongNguoiMua();
//                                            db.collection("HoaDon")
//                                                    .whereEqualTo("trangThai", "Giao thành công")
//                                                    .get()
//                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                                        @Override
//                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                                            if (task.isSuccessful()) {
//                                                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                                                    for(ChiTietHoaDon cthd:document.toObject(HoaDon.class).getChiTietHoaDon()){
//                                                                        if(cthd.getSanPhamId().equals(sanPhamId)){
//                                                                            db.collection("SanPham")
//                                                                                    .document(sanPhamId).update("soLuongNguoiMua",soLuongCu+1);
//                                                                        }
//                                                                    }
//                                                                }
//                                                            } else {
//                                                                Log.d("TAG", "Error getting documents: ", task.getException());
//                                                            }
//                                                        }
//                                                    });
//                                    }
//                                } else {
//                                    Log.d("TAG", "Error getting documents: ", task.getException());
//                                }
//                            }
//                        });


                db.collection("SanPham")
                        .orderBy("soLuongNguoiMua", Query.Direction.DESCENDING)
                        .limit(Integer.parseInt(spinner.getSelectedItem().toString()))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                List<Integer> soLanList=new ArrayList<>();
                                List<String> tenSanPham=new ArrayList<>();
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if(document.toObject(SanPham.class).getSoLuongNguoiMua()>0){
                                            tenSanPham.add(document.toObject(SanPham.class).getTenSanPham());
                                            soLanList.add(document.toObject(SanPham.class).getSoLuongNguoiMua());
                                        }
                                    }
                                    setChart(tenSanPham,soLanList);
                                } else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });
//                db.collection("SanPham")
//                        .limit(Integer.parseInt(spinner.getSelectedItem().toString()))
//                        .addSnapshotListener( new EventListener<QuerySnapshot>() {
//                            @Override
//                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                                if (e!=null){
//                                    return;
//                                }
//                                List<SanPham> sanPhams = new ArrayList<>();
//
//                                if (queryDocumentSnapshots != null) {
//                                    System.out.println(queryDocumentSnapshots.getDocuments().size());
//                                    if(queryDocumentSnapshots.getDocuments().size()>0) {
//                                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
//                                            sanPhams.add(doc.toObject(SanPham.class));
//                                            System.out.println(doc.toObject(SanPham.class).getTenSanPham());
//                                            final String maSanPham=doc.toObject(SanPham.class).getSanPhamId();
//                                            final int soLuongNguoiMua=doc.toObject(SanPham.class).getSoLuongNguoiMua();
//                                            db.collection("HoaDon")
//                                                    .whereEqualTo("trangThai","Giao thành công")
//                                                    .get()
//                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                                        @Override
//                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                                            if (task.isSuccessful()) {
//                                                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                                                    for(ChiTietHoaDon cthd:document.toObject(HoaDon.class).getChiTietHoaDon()){
//                                                                        if(cthd.getSanPhamId().equals(maSanPham)){
//                                                                            db.collection("SanPham")
//                                                                                    .document(maSanPham)
//                                                                                    .update("soLuongNguoiMua",soLuongNguoiMua+1);
//                                                                        }
//                                                                    }
//                                                                }
//                                                            } else {
//                                                                Log.d("TAG", "Error getting documents: ", task.getException());
//                                                            }
//                                                        }
//                                                    });
//                                        }
//                                    }
//
//                                } else{
//                                    Log.d("TAG", "Current data: null");
//                                }
//
//                            }
//                        });

//                db.collection("KhachHang")
//                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                            @Override
//                            public void onEvent(@Nullable QuerySnapshot value,
//                                                @Nullable FirebaseFirestoreException e) {
//                                if (e != null) {
//                                    Log.w("TAG", "Listen failed.", e);
//                                    return;
//                                }
//
//                                List<KhachHang> khachHangs = new ArrayList<>();
//                                for (QueryDocumentSnapshot doc : value) {
//                                    if (doc != null) {
//                                        khachHangs.add(doc.toObject(KhachHang.class));
//                                    }
//                                }
//                                System.out.println("Số lượng khách hàng: "+khachHangs.size());
//                            }
//                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //

    }

    public void setChart(List<String> tenSanPhamArray,List<Integer> soLanArray){
        ArrayList values1 = new ArrayList();

        for(int i=0;i<soLanArray.size();i++){
            values1.add(new BarEntry(i, soLanArray.get(i)));
        }

//        ArrayList valuesWomen = new ArrayList();
//
//        valuesWomen.add(new BarEntry(0, 155f));
//        valuesWomen.add(new BarEntry(1, 1040f));
//        valuesWomen.add(new BarEntry(2, 1133f));
//        valuesWomen.add(new BarEntry(3, 1240f));
//        valuesWomen.add(new BarEntry(4, 1369f));
//        valuesWomen.add(new BarEntry(5, 1487f));
//        valuesWomen.add(new BarEntry(6, 1501f));
//        valuesWomen.add(new BarEntry(7, 1645f));
//        valuesWomen.add(new BarEntry(8, 1578f));
//        valuesWomen.add(new BarEntry(9, 1695f));
//        valuesWomen.add(new BarEntry(10, 1578f));
//        valuesWomen.add(new BarEntry(11, 1695f));

        ArrayList month = new ArrayList();

        for(int i=0;i<tenSanPhamArray.size();i++){
            month.add(tenSanPhamArray.get(i));
        }

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));
        xAxis.setLabelRotationAngle(-45);
//        xAxis.setCenterAxisLabels(true);

        // create 2 datasets
        BarDataSet set1 = new BarDataSet(values1, "Số lần sản phẩm được mua");
        set1.setColor(Color.YELLOW);
//        BarDataSet set2 = new BarDataSet(valuesWomen, "Hóa đơn bán thành công");
//        set2.setColor(Color.RED);


        chart.animateY(2000);
        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
// (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"
        BarData data = new BarData(set1);
//        data.setBarWidth(barWidth);
        chart.setData(data);
        chart.setVisibleXRangeMaximum(5);

//        chart.groupBars(0f,groupSpace,barSpace);
        chart.invalidate();

    }


    // Function to remove duplicates from an ArrayList
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }
}
