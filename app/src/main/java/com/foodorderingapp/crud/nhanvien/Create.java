package com.foodorderingapp.crud.nhanvien;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.NhanVien;
import com.foodorderingapp.model.TaiKhoan;
import com.foodorderingapp.model.VaiTro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Create extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNhanVienGiaoHang();

    }

    void createNhanVienGiaoHang(){
        db.collection("VaiTro")
                .whereEqualTo("moTa","Nhân viên giao hàng")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            VaiTro vaiTro=null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                vaiTro=document.toObject(VaiTro.class);
                                break;
                            }
                            if(vaiTro!=null){
                                final TaiKhoan taiKhoan=new TaiKhoan();
                                taiKhoan.setPassword("123456");
                                taiKhoan.setUserName("nvgh");
                                taiKhoan.setKichHoat(1);
                                taiKhoan.setNgayTao(new GregorianCalendar(2020,Calendar.JANUARY,20).getTime());
                                taiKhoan.setVaiTroId(vaiTro.getVaiTroId());
                                db.collection("TaiKhoan")
                                        .add(taiKhoan)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                documentReference.update("taiKhoanId",documentReference.getId());
                                                //add taiKhoan to nhanVien
                                                NhanVien nhanVien=new NhanVien();
                                                nhanVien.setDiaChi("Hồ Chí Minh");
                                                nhanVien.setEmail("kafjsdk@Gmail.com");
                                                nhanVien.setHo("Lê");
                                                nhanVien.setNgaySinh(new GregorianCalendar(2000, Calendar.JANUARY,15).getTime());
                                                nhanVien.setSDT("012225555");
                                                nhanVien.setTen("Văn B");
                                                nhanVien.setTaiKhoanId(documentReference.getId());
                                                db.collection("NhanVien")
                                                        .add(nhanVien)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                documentReference.update("nhanVienId",documentReference.getId());
                                                            }
                                                        });
                                            }
                                        });
                            }
                        }
                    }
                });
    }

    void createNhanVienBanHang(){
        db.collection("VaiTro")
                .whereEqualTo("moTa","Nhân viên bán hàng")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            VaiTro vaiTro=null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                vaiTro=document.toObject(VaiTro.class);
                                break;
                            }
                            if(vaiTro!=null){
                                final TaiKhoan taiKhoan=new TaiKhoan();
                                taiKhoan.setPassword("123456");
                                taiKhoan.setUserName("nvbh");
                                taiKhoan.setKichHoat(1);
                                taiKhoan.setNgayTao(new GregorianCalendar(2020,Calendar.JANUARY,20).getTime());
                                taiKhoan.setVaiTroId(vaiTro.getVaiTroId());
                                db.collection("TaiKhoan")
                                        .add(taiKhoan)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                documentReference.update("taiKhoanId",documentReference.getId());
                                                //add taiKhoan to nhanVien
                                                NhanVien nhanVien=new NhanVien();
                                                nhanVien.setDiaChi("Hà Nội");
                                                nhanVien.setEmail("tkafs@Gmail.com");
                                                nhanVien.setHo("Lê");
                                                nhanVien.setNgaySinh(new GregorianCalendar(2000, Calendar.JANUARY,15).getTime());
                                                nhanVien.setSDT("012225555");
                                                nhanVien.setTen("Văn A");
                                                nhanVien.setTaiKhoanId(documentReference.getId());
                                                db.collection("NhanVien")
                                                        .add(nhanVien)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                documentReference.update("nhanVienId",documentReference.getId());
                                                            }
                                                        });
                                            }
                                        });
                            }
                        }
                    }
                });
    }
}
