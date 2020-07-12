package com.foodorderingapp.activity.client.homefragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;

import com.foodorderingapp.model.ChiTietHoaDon;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.HoaDon;
import com.foodorderingapp.model.KhachHang;
import com.foodorderingapp.model.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ItemFragment extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView ten;
    TextView gia;
    ImageView anh;
    Button mua;
    TextView loigioithieu;
    ImageButton add, subtract;
    TextView sl;
    SanPham data;
    ArrayList<DanhGia> danhGias =null;
    ListView listView;
    private String taiKhoanId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        AnhXa();
        this.taiKhoanId = (String) getIntent().getSerializableExtra("taiKhoanId");
        System.out.println("Mã tài khoản của khách hàng vừa đăng nhập là: " + this.taiKhoanId);
        ten.setText(data.getTenSanPham());
        gia.setText(data.getGiaBanLe()+"");
        sl.setText("1");
        mua.setText(data.getGiaBanLe()+"");
        new  DownLoadImageTask(anh).execute(data.getHinhAnhId());
        loigioithieu.setText(data.getMoTa());
        db.collection("DanhGia")
                .whereEqualTo("sanPhamId",data.getSanPhamId())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }
                        ArrayList<DanhGia> d = new ArrayList<>();
                        if (queryDocumentSnapshots != null) {
                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                System.out.println("Yes");
                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                    d.add(doc.toObject(DanhGia.class));
                                }
                                danhGias = d;
                                DanhGiaAdapter adapter = new DanhGiaAdapter(ItemFragment.this, danhGias);
                                listView.setAdapter(adapter);
                            }
                        }
                    }
                });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bamNutAdd();
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bamNutSubtract();
            }
        });



        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyền dữ liệu
                db.collection("HoaDon")
                        .whereEqualTo("taiKhoanId",taiKhoanId)
                        .whereEqualTo("trangThai","Chờ xử lý")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                final ArrayList<HoaDon> hoaDon1ArrayList = new ArrayList<>();
                                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                    hoaDon1ArrayList.add(documentSnapshot.toObject(HoaDon.class));
                                }
                                if (hoaDon1ArrayList.size()==0) {
                                    ChiTietHoaDon c1 = new ChiTietHoaDon(data.getSanPhamId(),data.getTenSanPham(),data.getGiaBanLe(),Integer.parseInt((String) sl.getText()),data.getGiaBanLe()*Integer.parseInt((String) sl.getText()),false);
                                    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<ChiTietHoaDon>();
                                    chiTietHoaDonList.add(c1);
                                    HoaDon hoaDon=new HoaDon();
                                    hoaDon.setHoaDonId(null);
                                    hoaDon.setChiTietHoaDon(chiTietHoaDonList);
                                    hoaDon.setNgayTao(new Date());
                                    hoaDon.setNgayGiao(new Date());
                                    hoaDon.setPhiShip(0);
                                    hoaDon.setQuan("aaa");
                                    hoaDon.setSoNha("aaa");
                                    hoaDon.setTongTienThanhToan(c1.getTongTien()+hoaDon.getPhiShip());
                                    hoaDon.setMaKhuyenMai(null);
                                    hoaDon.setTienKhuyenMai(0);
                                    hoaDon.setTrangThai("Chờ xử lý");
                                    hoaDon.setTaiKhoanId(taiKhoanId);
                                    hoaDon.setThoiGianDatHang(new Date());
                                    hoaDon.setThoiGianChoGiaoHang(null);
                                    hoaDon.setThoiGianDangGiaoHang(null);
                                    hoaDon.setThoiGianGiaoHangThanhCong(null);
                                    hoaDon.setThoiGianHuy(null);
                                    db.collection("HoaDon")
                                            .add(hoaDon)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            documentReference.update("hoaDonId",documentReference.getId());
                                            System.out.println("Update thành côngggggggggggggggggggggggggggggggggggggg");
                                        }
                                    });
                                    finish();

                                } else {
                                    HoaDon hoadon = hoaDon1ArrayList.get(0);
                                    List<ChiTietHoaDon>  chiTietHoaDonList = hoadon.getChiTietHoaDon();
                                    float tongtien = data.getGiaBanLe()*Integer.parseInt((String)sl.getText());
                                    ChiTietHoaDon c1 = new ChiTietHoaDon(data.getSanPhamId(),data.getTenSanPham(),data.getGiaBanLe(),Integer.parseInt((String) sl.getText()),tongtien,false);
                                    chiTietHoaDonList.add(c1);
                                    float tongtienthanhtoan =0;
                                    for(int i =0;i<chiTietHoaDonList.size();i++){
                                        tongtienthanhtoan+=chiTietHoaDonList.get(i).getTongTien();
                                    }
                                    tongtienthanhtoan += hoadon.getPhiShip();
                                    tongtienthanhtoan -= hoadon.getTienKhuyenMai();
                                    System.out.println(tongtienthanhtoan);
                                    db.collection("HoaDon").document(hoadon.getHoaDonId()).update("chiTietHoaDon",chiTietHoaDonList,"tongTienThanhToan",tongtienthanhtoan)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    System.out.println("Update thang cong");
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            System.out.println("Update that bai");

                                        }
                                    });
                                    finish();




                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("Không có");
                            }
                        });


                Toast.makeText(ItemFragment.this,"Thêm vào giỏ hàng thành công!",Toast.LENGTH_SHORT).show();












            }
        });





    }


    public void bamNutAdd(){
        sl.setText((Integer.parseInt((String) sl.getText())+1)+"");
        int soluong = Integer.parseInt((String) sl.getText());
        float tongtien = soluong * Float.parseFloat((String) gia.getText());
        mua.setText(tongtien+"");
    }

    public void bamNutSubtract(){
        if ((Integer.parseInt((String) sl.getText())==2) || (Integer.parseInt((String) sl.getText())==1)) {
            sl.setText(1+"");
            mua.setText(gia.getText());
        } else {
            sl.setText((Integer.parseInt((String) sl.getText()) - 1) + "");
            int soluong = Integer.parseInt((String) sl.getText());
            float tongtien = soluong * Float.parseFloat((String) gia.getText());
            mua.setText(tongtien+"");
        }
    }
    public void AnhXa() {
        getIntent().getSerializableExtra("taiKhoanId");
        data = (SanPham) getIntent().getSerializableExtra("sanpham");
        listView = (ListView)findViewById(R.id.list_danhgia);
        taiKhoanId = (String) getIntent().getSerializableExtra("taiKhoanId");
        ten = (TextView)findViewById(R.id.tensp);
        gia = (TextView)findViewById(R.id.gia);
        anh = (ImageView)findViewById(R.id.hinhanhdialog);
        mua = (Button)findViewById(R.id.mua);
        loigioithieu = (TextView)findViewById(R.id.loigioithieu);
        add = (ImageButton)findViewById(R.id.addd);
        subtract = (ImageButton)findViewById(R.id.subtractt);
        sl  = (TextView)findViewById(R.id.soluong);
    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
    class DanhGiaAdapter extends ArrayAdapter<DanhGia> {
        Context context;
        List<DanhGia> danhGiaList;
        DanhGiaAdapter(Context context,List<DanhGia> danhGiaList){
            super(context,R.layout.rating,danhGiaList);
            this.context=context;
            this.danhGiaList=danhGiaList;
        }


        private class ViewHolder {
            RatingBar rt;
            TextView dg;
        }
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;
            if(convertView == null){
                LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row =layoutInflater.inflate(R.layout.rating,parent,false);
                holder = new ViewHolder();
                holder.rt=(RatingBar ) row.findViewById(R.id.rating);
                holder.dg=(TextView) row.findViewById(R.id.getRating);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }

            DanhGia danhgia = danhGiaList.get(position);
            holder.rt.setRating(danhgia.getHangMuc());
            holder.dg.setText(danhgia.getNhanXet());

            return row;
        }
    }
}
