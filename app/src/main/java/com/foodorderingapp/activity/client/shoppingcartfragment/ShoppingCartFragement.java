package com.foodorderingapp.activity.client.shoppingcartfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.foodorderingapp.R;
import com.foodorderingapp.dialog.DialogCoupon;
import com.foodorderingapp.dialog.DialogItem1;
import com.foodorderingapp.model.ChiTietHoaDon;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragement extends Fragment {
    final float[] doanhthukhachhang = new float[1];
    float doanhthusanpham;
    HoaDonAdapter adapter;
    String hoaDonId;
    float tam;
    float tongtien =0;
    float phiShip;
    TextView textViewTongTien;
    TextView textViewPhiShip;
    Spinner spinner;
    ArrayList<String> quan = new ArrayList<>();
    ArrayList<ChiTietHoaDon> chiTietHoaDon1s;
    ListView gridView;
    EditText diachi;
    private String taiKhoanId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button btn_thanhtoan;
    float tongtienn =0;
    Button makhuyenmai;
    TextView tienkhuyenmai;
    public ShoppingCartFragement(String taiKhoanId){
        this.taiKhoanId=taiKhoanId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("Mã tài khoản của khách hàng vừa đăng nhập là: "+this.taiKhoanId);

        return inflater.inflate(R.layout.shoppingcart_fragment,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);

        db.collection("KhachHang").document(taiKhoanId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                System.out.println("Doanh thu kahsch hang: " +documentSnapshot.toObject(KhachHang.class).getDoanhThu());
                doanhthukhachhang[0] = documentSnapshot.toObject(KhachHang.class).getDoanhThu();
            }
        });

        db.collection("HoaDon")
                .whereEqualTo("trangThai","Chờ xử lý")
                .whereEqualTo("taiKhoanId",taiKhoanId)
                .addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e!=null){
                            return;
                        }
                        ArrayList<HoaDon> h = new ArrayList<>();
                        if (queryDocumentSnapshots != null) {
                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                    h.add(doc.toObject(HoaDon.class));
                                }
                                tam = h.get(0).getPhiShip();
                                hoaDonId = h.get(0).getHoaDonId();
                                tongtienn = h.get(0).getTongTienThanhToan();
                                chiTietHoaDon1s = (ArrayList<ChiTietHoaDon>) h.get(0).getChiTietHoaDon();
                                adapter = new HoaDonAdapter(view.getContext(), chiTietHoaDon1s);
                                gridView.setAdapter(adapter);
                                textViewPhiShip.setText(h.get(0).getPhiShip()+"đ");
                                textViewTongTien.setText(h.get(0).getTongTienThanhToan() + "đ");
                                tienkhuyenmai.setText("- "+h.get(0).getTienKhuyenMai()+" đ");
                            }



                        } else{
                            Log.d("TAG", "Current data: null");
                        }

                    }
                });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chonQuan(position,tongtienn);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Tài khoản là: "+taiKhoanId);
                Bundle bundle = new Bundle();
                ChiTietHoaDon chitiethoadon = chiTietHoaDon1s.get(position);
                bundle.putSerializable("chitiethoadonn", chitiethoadon);
                bundle.putString("taiKhoanId",taiKhoanId);
                bundle.putString("hoaDonId",hoaDonId);
                bundle.putInt("position",position);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                DialogItem1 f = new DialogItem1();
                f.setArguments(bundle);
                f.show(ft, "dialog");
            }
        });
        makhuyenmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("hoaDonId",hoaDonId);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                DialogCoupon f = new DialogCoupon();
                f.setArguments(bundle);
                f.show(ft, "dialog");
            }
        });


        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(diachi.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập địa chỉ giao hàng!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(chiTietHoaDon1s.size()==0) {
                    Toast.makeText(getActivity(),"Vui lòng chọn sản phẩm!",Toast.LENGTH_SHORT).show();
                    return;
                }





                System.out.println(tongtienn);
                db.collection("KhachHang").document(taiKhoanId).update("doanhThu", doanhthukhachhang[0]+tongtienn);

                db.collection("HoaDon").document(hoaDonId).update("trangThai","Chờ giao hàng");
                Toast.makeText(getActivity(),"Đặt hàng thành công!",Toast.LENGTH_SHORT).show();


            }
        });
    }

    class HoaDonAdapter extends ArrayAdapter<ChiTietHoaDon> {
        Context context;
        List<ChiTietHoaDon> chiTietHoaDon1List;
        HoaDonAdapter(Context context,List<ChiTietHoaDon> hoaDons){
            super(context,R.layout.item_card,hoaDons);
            this.context=context;
            this.chiTietHoaDon1List=hoaDons;
        }


        private class ViewHolder {
            TextView sl;
            TextView sp;
            TextView tien;
        }
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;
            if(convertView == null){
                LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row =layoutInflater.inflate(R.layout.item_card,parent,false);
                holder = new ViewHolder();
                holder.sl=(TextView )row.findViewById(R.id.card_sl);
                holder.sp=(TextView) row.findViewById(R.id.card_sp);
                holder.tien =(TextView ) row.findViewById(R.id.card_st);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }

            ChiTietHoaDon chitiethoadon = chiTietHoaDon1List.get(position);
            holder.sl.setText(chitiethoadon.getSoLuong()+"");
            holder.sp.setText(chitiethoadon.getTenSanPham()+"");
            holder.tien.setText(chitiethoadon.getTongTien()+"đ");

            return row;
        }
    }
    public void AnhXa(View view){
        tienkhuyenmai = view.findViewById(R.id.tv_tiengiam);
        diachi = (EditText)view.findViewById(R.id.diachi);
        textViewTongTien = (TextView)view.findViewById(R.id.tv_tongtien);
        textViewPhiShip = (TextView)view.findViewById(R.id.tv_phiship);
        chiTietHoaDon1s = new ArrayList<ChiTietHoaDon>();
        gridView = (ListView)view.findViewById(R.id.lv_sp_giohang);
        btn_thanhtoan = (Button)view.findViewById(R.id.btn_thanhtoan);
        makhuyenmai= (Button)view.findViewById(R.id.btn_makhuyenmai);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        AnhXaQuan();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,quan);
        spinner.setAdapter(arrayAdapter);
    }

    public void AnhXaQuan(){
        quan.add("Chọn quận");
        quan.add("Quận 1");
        quan.add("Quận 2");
        quan.add("Quận 3");
        quan.add("Quận 4");
        quan.add("Quận 5");
        quan.add("Quận 6");
        quan.add("Quận 7");
        quan.add("Quận 8");
        quan.add("Quận 9");
        quan.add("Quận 10");
        quan.add("Quận 11");
        quan.add("Quận 12");
        quan.add("Quận Bình Tân");
        quan.add("Quận Bình Thạnh");
        quan.add("Quận Gò Vấp");
        quan.add("Quận Phú Nhuận");
        quan.add("Quận Tân Bình");
        quan.add("Quận Tân Phú");
        quan.add("Quận Thủ Đức");
        quan.add("Huyện Bình Chánh");
        quan.add("Huyện Cần Giờ");
        quan.add("Huyện Củ Chi");
        quan.add("Huyện Hóc Môn");
        quan.add("Huyện Nhà Bè");
    }

    public void chonQuan(int position,float tongtienn){
        switch (position){
            case 0:
                break;
            case 1:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);
                break;
            case 2:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 3:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 4:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);
                tam = phiShip;
                break;
            case 5:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 6:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 7:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 8:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 9:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);
                break;
            case 10:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

                break;
            case 11:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);
                break;
            case 12:
                phiShip = 10000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);
                break;
            default:
                phiShip = 20000;
                db.collection("HoaDon").document(hoaDonId).update("phiShip",phiShip,"tongTienThanhToan",phiShip+tongtienn-tam);

        }
    }
}
