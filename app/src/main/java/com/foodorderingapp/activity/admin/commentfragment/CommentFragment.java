package com.foodorderingapp.activity.admin.commentfragment;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.admin.productfragment.ItemDetail;
import com.foodorderingapp.dialog.DialogComment;
import com.foodorderingapp.dialog.DialogItem1;
import com.foodorderingapp.model.ChiTietHoaDon;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.SanPham;
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

public class CommentFragment extends Fragment {
    ArrayList<DanhGia> danhGias =null;
    RadioGroup nhomdanhgia;
    ListView listView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_comment_main,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                DanhGia danhgia = danhGias.get(position);
                System.out.println("Đây là tài khoản:  "+danhgia.getTaiKhoanId());
                System.out.println(danhgia.getDanhGiaId());
                bundle.putSerializable("danhgia", danhgia);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                DialogComment f = new DialogComment();
                f.setArguments(bundle);
                f.show(ft, "dialog");
            }
        });





        nhomdanhgia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.motsao:
                        db.collection("DanhGia")
                                .whereEqualTo("hangMuc",1)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        if (e != null) {
                                            Log.w("TAG", "Listen failed.", e);
                                            return;
                                        }
                                        System.out.println("AAAAAAAAAAAAAAAAAAAAÂ");
                                        ArrayList<DanhGia> d = new ArrayList<>();
                                        System.out.println(queryDocumentSnapshots.getDocuments().size());
                                        if (queryDocumentSnapshots != null) {
                                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                                System.out.println("Yes");
                                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                    d.add(doc.toObject(DanhGia.class));
                                                    System.out.println(d.get(0).getHangMuc());
                                                }

                                                danhGias = d;
                                                System.out.println("đánh giá:"+danhGias.get(0).getDanhGiaId());
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), danhGias);
                                                listView.setAdapter(adapter);
                                            }
                                            else {
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), d);
                                                listView.setAdapter(adapter);

                                            }
                                        }
                                    }
                                });
                        break;
                    case R.id.haisao:
                        db.collection("DanhGia")
                                .whereEqualTo("hangMuc",2)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        if (e != null) {
                                            Log.w("TAG", "Listen failed.", e);
                                            return;
                                        }
                                        System.out.println("AAAAAAAAAAAAAAAAAAAAÂ");
                                        ArrayList<DanhGia> d = new ArrayList<>();
                                        System.out.println(queryDocumentSnapshots.getDocuments().size());
                                        if (queryDocumentSnapshots != null) {
                                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                                System.out.println("Yes");
                                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                    d.add(doc.toObject(DanhGia.class));
                                                    System.out.println(d.get(0).getHangMuc());
                                                }

                                                danhGias = d;

                                                System.out.println("đánh giá:"+danhGias.get(0).getDanhGiaId());
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), danhGias);
                                                listView.setAdapter(adapter);
                                            }else {
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), d);
                                                listView.setAdapter(adapter);

                                            }
                                        }
                                    }
                                });
                        break;
                    case R.id.basao:
                        db.collection("DanhGia")
                                .whereEqualTo("hangMuc",3)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        if (e != null) {
                                            Log.w("TAG", "Listen failed.", e);
                                            return;
                                        }
                                        System.out.println("AAAAAAAAAAAAAAAAAAAAÂ");
                                        ArrayList<DanhGia> d = new ArrayList<>();
                                        System.out.println(queryDocumentSnapshots.getDocuments().size());
                                        if (queryDocumentSnapshots != null) {
                                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                                System.out.println("Yes");
                                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                    d.add(doc.toObject(DanhGia.class));
                                                    System.out.println(d.get(0).getHangMuc());
                                                }
                                                danhGias = d;
                                                System.out.println("đánh giá:"+danhGias.get(0).getDanhGiaId());
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), danhGias);
                                                listView.setAdapter(adapter);
                                            }
                                            else {
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), d);
                                                listView.setAdapter(adapter);

                                            }
                                        }
                                    }
                                });
                        break;
                    case R.id.bonsao:
                        db.collection("DanhGia")
                                .whereEqualTo("hangMuc",4)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        if (e != null) {
                                            Log.w("TAG", "Listen failed.", e);
                                            return;
                                        }
                                        System.out.println("AAAAAAAAAAAAAAAAAAAAÂ");
                                        ArrayList<DanhGia> d = new ArrayList<>();
                                        System.out.println(queryDocumentSnapshots.getDocuments().size());
                                        if (queryDocumentSnapshots != null) {
                                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                                System.out.println("Yes");
                                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                    d.add(doc.toObject(DanhGia.class));
                                                    System.out.println(d.get(0).getHangMuc());
                                                }
                                                danhGias = d;
                                                System.out.println("đánh giá:"+danhGias.get(0).getDanhGiaId());
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), danhGias);
                                                listView.setAdapter(adapter);
                                            }
                                            else {
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), d);
                                                listView.setAdapter(adapter);

                                            }
                                        }
                                    }
                                });
                        break;
                    case R.id.namsao:
                        db.collection("DanhGia")
                                .whereEqualTo("hangMuc",5)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        if (e != null) {
                                            Log.w("TAG", "Listen failed.", e);
                                            return;
                                        }
                                        System.out.println("AAAAAAAAAAAAAAAAAAAAÂ");
                                        ArrayList<DanhGia> d = new ArrayList<>();
                                        System.out.println(queryDocumentSnapshots.getDocuments().size());
                                        if (queryDocumentSnapshots != null) {
                                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                                System.out.println("Yes");
                                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                                    d.add(doc.toObject(DanhGia.class));
                                                    System.out.println(d.get(0).getHangMuc());
                                                }
                                                System.out.println("nam sao"+d.get(0).getSanPhamId());

                                                danhGias = d;
                                                System.out.println("đánh giá:"+danhGias.get(0).getDanhGiaId());
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), danhGias);
                                                listView.setAdapter(adapter);
                                            }else {
                                                DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), d);
                                                listView.setAdapter(adapter);

                                            }
                                        }
                                    }
                                });
                        break;
                }
            }
        });

    }
    public void AnhXa(View view){
        nhomdanhgia = (RadioGroup)view.findViewById(R.id.nhomdanhgia);
        listView = (ListView)view.findViewById(R.id.lv_dg);
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
                holder.rt=(RatingBar) row.findViewById(R.id.rating);
                holder.dg=(TextView) row.findViewById(R.id.getRating);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }

            DanhGia danhgia = danhGiaList.get(position);
            holder.rt.setEnabled(false);
            holder.rt.setRating(danhgia.getHangMuc());
            holder.dg.setText(danhgia.getNhanXet());

            return row;
        }
    }
}
