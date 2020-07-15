package com.foodorderingapp.activity.admin.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CustomerStatisticsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private BarChart chart;
    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_customerstatic_main,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart=view.findViewById(R.id.customerBarChart);
        //Lay du lieu nam hien thi len Spinner
        spinner=view.findViewById(R.id.customer_spinner);
        System.out.println(Integer.parseInt(spinner.getSelectedItem().toString()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db.collection("KhachHang")
                        .orderBy("doanhThu", Query.Direction.DESCENDING).limit(Integer.parseInt(spinner.getSelectedItem().toString()))
                        .addSnapshotListener( new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                if (e!=null){
                                    return;
                                }
                                List<KhachHang> khachHangs = new ArrayList<>();

                                if (queryDocumentSnapshots != null) {
                                    if(queryDocumentSnapshots.getDocuments().size()>0) {
                                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                            khachHangs.add(doc.toObject(KhachHang.class));
                                            System.out.println(doc.toObject(KhachHang.class).getDoanhThu());
                                            System.out.println(doc.toObject(KhachHang.class).getTen());
                                        }

                                    }
                                    System.out.println("Số lượng khách hàng: "+khachHangs.size());
                                    String[] tenKhachHangArray=new String[khachHangs.size()];
                                    Float[] doanhThuArray=new Float[khachHangs.size()];
                                    for(int i=0;i<khachHangs.size();i++){
                                        tenKhachHangArray[i]=khachHangs.get(i).getTen();
                                        doanhThuArray[i]=khachHangs.get(i).getDoanhThu();
                                        System.out.println(doanhThuArray[i]);
                                    }
                                    System.out.println("Số lượng tên: "+tenKhachHangArray.length);
                                    System.out.println("Số lượng danh thu: "+doanhThuArray.length);
                                    setChart(tenKhachHangArray,doanhThuArray);
                                } else{
                                    Log.d("TAG", "Current data: null");
                                }

                            }
                        });

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

    public void setChart(String[] tenKhachHangArray,Float[] doanhThuArray){
        ArrayList values1 = new ArrayList();

        for(int i=0;i<doanhThuArray.length;i++){
            values1.add(new BarEntry(i, doanhThuArray[i]));
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

        for(int i=0;i<tenKhachHangArray.length;i++){
            month.add(tenKhachHangArray[i]);
        }

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));
//        xAxis.setCenterAxisLabels(true);

        // create 2 datasets
        BarDataSet set1 = new BarDataSet(values1, "Doanh thu kháchh hàng");
        set1.setColor(Color.RED);
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
        chart.setVisibleXRangeMaximum(10);
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
