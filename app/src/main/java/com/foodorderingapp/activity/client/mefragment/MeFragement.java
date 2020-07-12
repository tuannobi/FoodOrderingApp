//package com.foodorderingapp.activity.client.mefragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.foodorderingapp.R;
//import com.foodorderingapp.model.KhachHang;
//import com.foodorderingapp.model.TaiKhoan;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.text.SimpleDateFormat;
//
//
//public class MeFragement extends Fragment {
//
//    private String taiKhoanId;
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    public MeFragement(){}
//
//    public MeFragement(String taiKhoanId){
//        this.taiKhoanId=taiKhoanId;
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        System.out.println("Mã tài khoản của khách hàng vừa đăng nhập là: "+this.taiKhoanId);
//        return inflater.inflate(R.layout.me_fragment,container,false);
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
//        //anh xa
//        ImageView image=(ImageView) view.findViewById(R.id.imageView);
//        super.onViewCreated(view, savedInstanceState);
//
//
//        db.collection("KhachHang")
//                .whereEqualTo("taiKhoanId",taiKhoanId)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            KhachHang khachhang = null;
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                khachhang = document.toObject(KhachHang.class);
//                                break;
//                            }
//                            System.out.println(khachhang.toString());
//                            if(khachhang!=null) {
//                                TextView Ho = (TextView) view.findViewById(R.id.ho);//final nó k thay đổi giá trị
//                                TextView Ten = (TextView) view.findViewById(R.id.ten);
//                                TextView NgSinh = (TextView) view.findViewById(R.id.ngaysinh);
//                                TextView Email = (TextView) view.findViewById(R.id.email);
//                                TextView Sdt = (TextView) view.findViewById(R.id.sdt);
//                                TextView Dchi = (TextView) view.findViewById(R.id.dchi);
//
//
//
//
//                                Ho.setText(Ho.getText().toString() + khachhang.getHo());
//                                Ten.setText(Ten.getText().toString() + khachhang.getTen());
//                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
//                                NgSinh.setText(NgSinh.getText()+simpleDateFormat.format(khachhang.getNgaySinh()));
//                                Email.setText(Email.getText().toString() + khachhang.getEmail());
//                                Sdt.setText(Sdt.getText().toString() + khachhang.getSDT());
//                                Dchi.setText(Dchi.getText().toString() + khachhang.getDiaChi());
//
//
//                            }
//
//
//                        }
//
//                    }
//                });
//
//        db.collection("TaiKhoan")
//                .whereEqualTo("taiKhoanId",taiKhoanId)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task){
//                        if(task.isSuccessful()){
//                            TaiKhoan taikhoan=null;
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                taikhoan = document.toObject(TaiKhoan.class);
//                                break;
//                            }
//                            if(taikhoan!=null) {
//                                TextView Tendn = (TextView) view.findViewById(R.id.tendn);
//                                Tendn.setText(Tendn.getText().toString() + taikhoan.getUserName());
//                                System.out.println("Tài khoản ID ");
//                                Button sua = (Button) view.findViewById(R.id.button);
//                                sua.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Intent iSubsua = new Intent(getContext(), UpdateThongTin.class);
//                                        iSubsua.putExtra("taiKhoanId", taiKhoanId);
//                                        System.out.println("ID ở MeFragment: "+taiKhoanId);
//
//                                        startActivity(iSubsua);
//
//                                    }
//                                });
//                            }
//                        }
//                    }
//                });
//
//
//
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//}
