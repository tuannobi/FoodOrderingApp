package com.foodorderingapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.client.homefragment.HomeFragement;
import com.foodorderingapp.model.SanPham;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    private HomeFragement context;
    private int layout;
    private List<SanPham> sanPhamList,tempList;
    CustomFilter cs;
    public SanPhamAdapter(HomeFragement context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
        tempList = sanPhamList;
    }
    public int getCount() {
        return sanPhamList.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public Filter getFilter(){
        if (cs==null){
            cs = new CustomFilter();

        }
        return cs;
    }
    class CustomFilter extends Filter {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint!=null && constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<SanPham> filters =  new ArrayList<SanPham>();
                for(int i=0;i<tempList.size();i++){
                    if(tempList.get(i).getTenSanPham().toUpperCase().contains(constraint)){
                        SanPham sanPham = new SanPham(tempList.get(i).getSanPhamId(),tempList.get(i).getTenSanPham(),tempList.get(i).getGiaGoc(),tempList.get(i).getGiaBanLe(),tempList.get(i).getKho(),tempList.get(i).getTrangThai(),tempList.get(i).getPhanLoaiId(),tempList.get(i).getHinhAnhId(),tempList.get(i).getMoTa());
                        filters.add(sanPham);
                    }
                }
                filterResults.count = filters.size();
                filterResults.values = filters;


            }
            else {
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            sanPhamList = (ArrayList<SanPham>)results.values;
            notifyDataSetChanged();
        }
    }
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        ImageView ivHinh;
        ImageView ivNut;
        TextView textView1;
        TextView textView2;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new ViewHolder();
            holder.ivHinh = (ImageView) convertView.findViewById(R.id.iv1);
            holder.ivNut = (ImageView) convertView.findViewById(R.id.ib1);
            holder.textView1 = (TextView)convertView.findViewById(R.id.tv1);
            holder.textView2 = (TextView)convertView.findViewById(R.id.tv2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SanPham sanpham = sanPhamList.get(position);
        new  DownLoadImageTask(holder.ivHinh).execute(sanpham.getHinhAnhId());
//        holder.ivHinh.setImageResource(sanpham.getHinhAnhId());
        holder.ivNut.setImageResource(R.drawable.icon_add);
        holder.textView1.setText(sanpham.getTenSanPham());
        holder.textView2.setText(sanpham.getGiaBanLe()+"");


        return convertView;
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
