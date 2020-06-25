package com.foodorderingapp.crud.hoadon;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;
import com.foodorderingapp.model.HoaDon;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Create extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HoaDon hoaDon=new HoaDon();
        hoaDon.setNgayTao(new GregorianCalendar(2019, Calendar.FEBRUARY,22).getTime());
        hoaDon.setPhuongThucThanhToan("SHIP");
        hoaDon.setNgayTao(new GregorianCalendar(2019, Calendar.JANUARY,22).getTime());
        hoaDon.setSoNha("12/33");
        hoaDon.setQuan("Thủ Đức");
        hoaDon.setPhiShipId("2kbQJ9tudnUezjavmvW4");
        hoaDon.setTongTienThanhToan(100000);
        hoaDon.setTinNhan("Giao hàng trước 10h");
        hoaDon.setTrangThai("Chờ xử lý");
        hoaDon.setKhachHangId("4cRT0cgg0wbsvbuP0Etv");
        hoaDon.setNhanVienBanHangId("LVobUmppP7M5EyF2uNqF");
        hoaDon.setNhanVienGiaoHangId("As25Sy2TutkdrL2cbWdk");
        hoaDon.setThoiGianDatHang(new Date());
        hoaDon.setThoiGianChoGiaoHang(new Date());
        hoaDon.setThoiGianDangGiaoHang(new Date());
        hoaDon.setThoiGianGiaoHangThanhCong(new Date());
        db.collection("HoaDon")
                .add(hoaDon)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        documentReference.update("hoaDonId",documentReference.getId());
                    }
                });
    }
}
