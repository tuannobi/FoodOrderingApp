package com.foodorderingapp.dialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.SanPham;

import java.io.InputStream;
import java.net.URL;

public class DialogItem extends DialogFragment {
    TextView ten;
    TextView gia;
    ImageView anh;
    Button mua;
    TextView loigioithieu;
    ImageButton add, subtract;
    TextView sl;

    public static DialogItem newInstance() {
        DialogItem dialogItem = new DialogItem();
        SanPham sanPham = new SanPham(null,"a",1,2,3,"Có","Coffe","https://firebasestorage.googleapis.com/v0/b/foodorderingapp-85e14.appspot.com/o/images%2F5c8a4233-c294-4bb8-bd50-f32736f42f01?alt=media&token=0f6e79aa-d821-4afe-8f06-451a9b9b27c1","abc");
        Bundle args = new Bundle();
        args.putParcelable("sanpham", (Parcelable) sanPham);
        dialogItem.setArguments(args);
        return dialogItem;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_item, container);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SanPham data = (SanPham) getArguments().getSerializable("key");
        ten = (TextView)view.findViewById(R.id.tensp);
        gia = (TextView)view.findViewById(R.id.gia);
        anh = (ImageView)view.findViewById(R.id.hinhanhdialog);
        mua = (Button)view.findViewById(R.id.mua);
        loigioithieu = (TextView)view.findViewById(R.id.loigioithieu);
        add = (ImageButton)view.findViewById(R.id.addd);
        subtract = (ImageButton)view.findViewById(R.id.subtractt);
        sl  = (TextView)view.findViewById(R.id.soluong);

        ten.setText(data.getTenSanPham());
        gia.setText(data.getGiaBanLe()+"");
        sl.setText("1");
        mua.setText(data.getGiaBanLe()+"");
        new  DownLoadImageTask(anh).execute(data.getHinhAnhId());
        loigioithieu.setText(data.getMoTa());
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
}
