package com.foodorderingapp.activity.admin.statistics;

import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.myordersfragment.lichsudonhangfragment.LichSuDonHangFragment;
import com.foodorderingapp.model.HoaDon;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStatisticsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private BarChart chart;
    private Spinner spinner;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_orderstatic_main,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart=view.findViewById(R.id.orderBarChart);
        spinner=view.findViewById(R.id.order_spinner);
        //Lay du lieu nam hien thi len Spinner
        db.collection("HoaDon")
                .whereIn("trangThai",new ArrayList<>(Arrays.asList("Giao thành công")))
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
                                    List<Integer> yearList=new ArrayList<>();
                                    for(HoaDon hoaDon:hoaDons){
                                            yearList.add(Integer.parseInt((String) DateFormat.format("yyyy",hoaDon.getThoiGianGiaoHangThanhCong())));
                                    }
                                    ArrayList<Integer> newHoaDonList=removeDuplicates((ArrayList<Integer>) yearList);
                                    System.out.println("Spinner hien co: "+newHoaDonList.size());
                                    ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,newHoaDonList);
                                    spinner.setAdapter(arrayAdapter);
                                }
                            }



                        } else{
                            Log.d("TAG", "Current data: null");
                        }

                    }
                });
        //

        //Event for Spinner change
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
                db.collection("HoaDon")
                        .whereIn("trangThai",new ArrayList<>(Arrays.asList("Giao thành công")))
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
                                            List<Integer> yearList=new ArrayList<>();
                                            for(HoaDon hoaDon:hoaDons){
                                                    yearList.add(Integer.parseInt((String) DateFormat.format("yyyy",hoaDon.getThoiGianGiaoHangThanhCong())));
                                            }
                                            //Tinh doanh thu theo thang
                                            Integer selectedYear=Integer.parseInt(spinner.getSelectedItem().toString());
                                            System.out.println("nam da chon: "+spinner.getSelectedItem().toString());
                                            List<HoaDon> hoaDonTheoNam=new ArrayList<>();
                                            for(HoaDon hoaDonTemp:hoaDons){
                                                Calendar calendar = new GregorianCalendar();
                                                calendar.setTime(hoaDonTemp.getThoiGianGiaoHangThanhCong());
                                                System.out.println(calendar.get(Calendar.YEAR));
                                                if(calendar.get(Calendar.YEAR)==selectedYear){
                                                    hoaDonTheoNam.add(hoaDonTemp);
                                                    System.out.println(hoaDonTemp.getTongTienThanhToan());
                                                }
                                            }
                                            System.out.println("So luong hoa don cua nam da chon: "+hoaDonTheoNam.size());
                                            Float[] arrayDoanhThu=new Float[12];
                                            Arrays.fill(arrayDoanhThu,0f);
                                            for(HoaDon hoaDonTemp:hoaDonTheoNam){
                                                Calendar calendar = new GregorianCalendar();
                                                calendar.setTime(hoaDonTemp.getThoiGianGiaoHangThanhCong());
                                                switch (calendar.get(Calendar.MONTH)){
                                                    case 0:
                                                        arrayDoanhThu[0]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 1:
                                                        arrayDoanhThu[1]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 2:
                                                        arrayDoanhThu[2]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 3:
                                                        arrayDoanhThu[3]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 4:
                                                        arrayDoanhThu[4]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 5:
                                                        arrayDoanhThu[5]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 6:
                                                        arrayDoanhThu[6]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 7:
                                                        arrayDoanhThu[7]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 8:
                                                        arrayDoanhThu[8]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 9:
                                                        arrayDoanhThu[9]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 10:
                                                        arrayDoanhThu[10]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                    case 11:
                                                        arrayDoanhThu[11]+=hoaDonTemp.getTongTienThanhToan();
                                                        break;
                                                }
                                            }
                                            //
                                            System.out.println("Thuc hien thay doi");
                                            setChart(arrayDoanhThu);
                                        }
                                    }



                                } else{
                                    Log.d("TAG", "Current data: null");
                                }

                            }
                        });
                //

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setChart(Float[] doanhThuTheoThang){
        ArrayList values1 = new ArrayList();

        for(int i=0;i<doanhThuTheoThang.length;i++){
            values1.add(new BarEntry(i, doanhThuTheoThang[i].floatValue()));
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

        month.add("T1");
        month.add("T2");
        month.add("T3");
        month.add("T4");
        month.add("T5");
        month.add("T6");
        month.add("T7");
        month.add("T8");
        month.add("T9");
        month.add("T10");
        month.add("T11");
        month.add("T12");

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));
//        xAxis.setCenterAxisLabels(true);

        // create 2 datasets
        BarDataSet set1 = new BarDataSet(values1, "Doanh thu hóa đơn");
        set1.setColor(Color.BLUE);
//        BarDataSet set2 = new BarDataSet(valuesWomen, "Hóa đơn bán thành công");
//        set2.setColor(Color.RED);


        chart.animateY(5000);
        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
// (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"
        BarData data = new BarData(set1);
//        data.setBarWidth(barWidth);
        chart.setData(data);
        chart.setVisibleXRangeMaximum(6);
//        chart.groupBars(0f,groupSpace,barSpace);
        chart.invalidate();

        Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
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
