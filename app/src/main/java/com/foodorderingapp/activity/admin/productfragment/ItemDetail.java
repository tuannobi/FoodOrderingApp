package com.foodorderingapp.activity.admin.productfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.homefragment.ItemFragment;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.SanPham;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ItemDetail extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText ten;
    EditText giabanle;
    EditText giagoc;
    TextView doanhthu;
    ImageView anh;
    EditText mota;
    RadioGroup phanloai;
    RadioButton phanloaithucuong,phanloaithucan,trangthaico,trangthaikhong;
    RadioGroup trangthai;
    SanPham data;
    ArrayList<DanhGia> danhGias =null;
    ListView listView;
    Button suathongtin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_admin);
        AnhXa();
        ten.setText(data.getTenSanPham());
        giabanle.setText(data.getGiaBanLe()+"");
        giagoc.setText(data.getGiaGoc()+"");
        doanhthu.setText(data.getDoanhThu()+"");
        new  DownLoadImageTask(anh).execute(data.getHinhAnhId());
        mota.setText(data.getMoTa());
        System.out.println(data.getTrangThai());
        if(data.getPhanLoaiId().equals("Thức ăn")){
            phanloaithucan.setChecked(true);
        } else {
            phanloaithucuong.setChecked(true);
        }
        if(data.getTrangThai().equals("Không")) {
            trangthaikhong.setChecked(true);
        }else {
            trangthaico.setChecked(true);
        }
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
                                DanhGiaAdapter adapter = new DanhGiaAdapter(ItemDetail.this, danhGias);
                                listView.setAdapter(adapter);
                            }
                        }
                    }
                });

        suathongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phanLoai,trangThai;
                if(phanloaithucan.isChecked()){
                    phanLoai = "Thức ăn";
                } else {
                    phanLoai = "Thức uống 1";
                }
                if(trangthaico.isChecked()){
                    trangThai = "Có";
                }else{
                    trangThai =  "Không";
                }
                SanPham sanpham = new SanPham(data.getSanPhamId(),data.getTenSanPham(),Float.parseFloat(giagoc.getText().toString()),Float.parseFloat(giabanle.getText().toString()),trangThai,phanLoai,data.getHinhAnhId(),mota.getText().toString(),data.getDoanhThu());
                db.collection("SanPham").document(data.getSanPhamId()).set(sanpham).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ItemDetail.this,"Sửa thông tin sản phẩm thành công!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chuyen= new Intent(ItemDetail.this, ItemDetail.class);
                DanhGia danhgia = danhGias.get(position);
                chuyen.putExtra("danhgia",(Serializable) danhgia);
                startActivity(chuyen);
            }
        });

    }
    public void AnhXa() {
        data = (SanPham) getIntent().getSerializableExtra("sanpham");
        listView = (ListView)findViewById(R.id.list_danhgia_admin);
        ten = (EditText)findViewById(R.id.tensp_admin);
        giabanle = (EditText)findViewById(R.id.giabanle_admin);
        giagoc = (EditText)findViewById(R.id.giagoc_admin);
        doanhthu = (TextView) findViewById(R.id.doanhthu_admin);
        anh = (ImageView)findViewById(R.id.hinhanhdialog_admin);
        mota = (EditText)findViewById(R.id.loigioithieu_admin);
        phanloai = (RadioGroup)findViewById(R.id.phanloaiphanloai);
        trangthai = (RadioGroup)findViewById(R.id.trangthaitrangthai);
        phanloaithucan = (RadioButton)findViewById(R.id.phanloaithucan);
        phanloaithucuong = (RadioButton)findViewById(R.id.phanloaithucuong);
        trangthaico = (RadioButton)findViewById(R.id.trangthaico);
        trangthaikhong = (RadioButton)findViewById(R.id.trangthaikhong);
        suathongtin = (Button)findViewById(R.id.nutsua);
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
