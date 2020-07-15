package com.foodorderingapp.activity.client.myordersfragment.lichsudonhangfragment;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import androidx.fragment.app.FragmentManager;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.homefragment.ItemFragment;
import com.foodorderingapp.dialog.DanhGiaDialog;
import com.foodorderingapp.model.ChiTietHoaDon;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChiTietDonHangActivity extends AppCompatActivity {

    private List<ChiTietHoaDon> chiTietHoaDons;
    private float tongTienCanThanhToan;
    private ListView listView;
    private String taiKhoanId;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdonhang_activity);
        chiTietHoaDons=new ArrayList<>();
        chiTietHoaDons= (ArrayList<ChiTietHoaDon>) getIntent().getExtras().getSerializable("chiTietDonHang");
        taiKhoanId=getIntent().getStringExtra("taiKhoanId");
        tongTienCanThanhToan=getIntent().getExtras().getFloat("tongTienCanThanhToan");
        listView=findViewById(R.id.chiTietDonHangView);
        System.out.println("So luong chi tiet don hang: "+chiTietHoaDons.size());
        MyAdapter myAdapter=new MyAdapter(getApplicationContext(),chiTietHoaDons);
        listView.setAdapter(myAdapter);
    }

    class MyAdapter extends ArrayAdapter<ChiTietHoaDon> {
        Context context;
        List<ChiTietHoaDon> chiTietHoaDons;
        MyAdapter(Context context,List<ChiTietHoaDon> chiTietHoaDons ){
            super(context,R.layout.chitietdonhang_row,chiTietHoaDons);
            this.context=context;
            this.chiTietHoaDons=chiTietHoaDons;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.chitietdonhang_row,parent,false);
            TextView tenSanPham=row.findViewById(R.id.chiTiet_TenSanPham);
            TextView donGia=row.findViewById(R.id.ChiTiet_DonGia);
            TextView soLuong=row.findViewById(R.id.ChiTiet_SoLuong);
            TextView tongTien=row.findViewById(R.id.ChiTiet_TongTienThanhToan);
            Button button=row.findViewById(R.id.danhGiaButton);
            tenSanPham.setText(chiTietHoaDons.get(position).getTenSanPham());
            soLuong.setText("Số lượng: "+String.valueOf(chiTietHoaDons.get(position).getSoLuong()));
            tongTien.setText("Tổng tiền: "+String.valueOf(tongTienCanThanhToan));
            donGia.setText("Đơn giá: "+String.valueOf(chiTietHoaDons.get(position).getGia()));
            //

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        showEditDialog(chiTietHoaDons.get(position).getSanPhamId(),taiKhoanId);
                                        }
            });

            //
            return row;
        }
    }

    private void showEditDialog(String sanPhamId,String taiKhoanId) {
        FragmentManager fm = getSupportFragmentManager();
        DanhGiaDialog editNameDialogFragment = new  DanhGiaDialog("Đánh giá",sanPhamId,taiKhoanId);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }


}
