package com.foodorderingapp.activity.admin.productfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.SanPham;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FoodProductFragment extends Fragment {
    SanPhamAdapter1 adapter;
    SearchView searchView;
    ArrayList<SanPham> arraySanPham;
    ListView listView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.food_product_fragment,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        db.collection("SanPham")
                .whereEqualTo("phanLoaiId","Thức ăn")
                .whereEqualTo("trangThai","Có")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            return;
                        }
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            arraySanPham.add(doc.toObject(SanPham.class));
                        }

                        adapter =  new SanPhamAdapter1(view.getContext(),arraySanPham);
                        listView.setAdapter(adapter);
                    }
                });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }



    public void AnhXa(View view){
        searchView = (SearchView)view.findViewById(R.id.searchview_food);
        arraySanPham = new ArrayList<SanPham>();
        listView = (ListView) view.findViewById(R.id.listview_food);
    }
    class SanPhamAdapter1 extends ArrayAdapter<SanPham> {
        Context context;
        List<SanPham> sanPhamList,tempList;
        CustomFilter cs;
        SanPhamAdapter1(Context context,List<SanPham> sanPhamList){
            super(context,R.layout.item_admin,sanPhamList);
            this.context=context;
            this.sanPhamList=sanPhamList;
            this.tempList=sanPhamList;
        }
        public int getCount() {
            return sanPhamList.size();
        }
        public SanPham getItem(int position) {
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
                if (constraint != null && constraint.length() > 0) {
                    constraint = constraint.toString().toUpperCase();
                    ArrayList<SanPham> filters = new ArrayList<SanPham>();
                    for (int i = 0; i < tempList.size(); i++) {
                        if (tempList.get(i).getTenSanPham().toUpperCase().contains(constraint)) {
                            SanPham sanPham = new SanPham(tempList.get(i).getSanPhamId(), tempList.get(i).getTenSanPham(), tempList.get(i).getGiaGoc(), tempList.get(i).getGiaBanLe(), tempList.get(i).getTrangThai(), tempList.get(i).getPhanLoaiId(), tempList.get(i).getHinhAnhId(), tempList.get(i).getMoTa(),tempList.get(i).getDoanhThu());
                            filters.add(sanPham);
                        }
                    }
                    filterResults.count = filters.size();
                    filterResults.values = filters;


                } else {
                    filterResults.count = tempList.size();
                    filterResults.values = tempList;
                }

                return filterResults;
            }

            protected void publishResults(CharSequence constraint, FilterResults results) {
                sanPhamList = (ArrayList<SanPham>) results.values;
                notifyDataSetChanged();
            }
        }
        private class ViewHolder {
            ImageView ivHinh;
            TextView textView1;
            TextView textView2;
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;
            ViewHolder holder;
            if(convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(R.layout.item_admin, parent, false);
                holder = new ViewHolder();
                holder.ivHinh = (ImageView) v .findViewById(R.id.imageview1);
                holder.textView1 = (TextView)v .findViewById(R.id.tv_tensanpham);
                holder.textView2 = (TextView)v .findViewById(R.id.tv_trangthaithucsu);
                v.setTag(holder);

            } else {
                holder = (ViewHolder) v.getTag();
            }


            SanPham sanpham = sanPhamList.get(position);
            new  DownLoadImageTask(holder.ivHinh).execute(sanpham.getHinhAnhId());
//        holder.ivHinh.setImageResource(sanpham.getHinhAnhId());
            holder.textView1.setText(sanpham.getTenSanPham());
            holder.textView2.setText(sanpham.getTrangThai()+"");


            return v;
        }


        private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
            ImageView imageView;

            public DownLoadImageTask(ImageView imageView){
                this.imageView = imageView;
            }

            protected Bitmap doInBackground(String...urls){
                String urlOfImage = urls[0];
                Bitmap logo = null;
                try{
                    InputStream is = new URL(urlOfImage).openStream();
                    logo = BitmapFactory.decodeStream(is);
                }catch(Exception e){ // Catch the download exception
                    e.printStackTrace();
                }
                return logo;
            }
            protected void onPostExecute(Bitmap result){
                imageView.setImageBitmap(result);
            }
        }


    }
}
