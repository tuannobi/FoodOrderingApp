package com.foodorderingapp.activity.admin.promotionfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.activity.admin.productfragment.ItemDetail;
import com.foodorderingapp.model.ChiTietKhuyenMai;
import com.foodorderingapp.model.DanhGia;
import com.foodorderingapp.model.KhuyenMai;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PromotionFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button taokhuyenmai;
    ListView listView;
    ArrayList<KhuyenMai> khuyenMais =null;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_promotion_main,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        db.collection("KhuyenMai")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "Listen failed.", e);
                            return;
                        }
                        ArrayList<KhuyenMai> k = new ArrayList<>();
                        if (queryDocumentSnapshots != null) {
                            if(queryDocumentSnapshots.getDocuments().size()>0) {
                                System.out.println("Yes");
                                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                    k.add(doc.toObject(KhuyenMai.class));
                                    System.out.println(k.get(0).getKhuyenMaiId());
                                }
                                khuyenMais = k;
                                System.out.println(khuyenMais.size());
                                KhuyenMaiAdapter adapter = new KhuyenMaiAdapter(getContext(), khuyenMais);
                                listView.setAdapter(adapter);
                            }
                        }
                    }
                });
        taokhuyenmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked khuyen mai");
                Intent intent=new Intent(getContext(),ChiTietKhuyenMaiActivity.class);
                startActivity(intent);
            }
        });
    }
    private void AnhXa(View view){
        taokhuyenmai = view.findViewById(R.id.taokhuyenmai);
        listView = view.findViewById(R.id.danhsachkhuyenmai);
    }
    class KhuyenMaiAdapter extends ArrayAdapter<KhuyenMai> {
        Context context;
        List<KhuyenMai> khuyenMaiList;
        KhuyenMaiAdapter(Context context,List<KhuyenMai> khuyenMaiList){
            super(context,R.layout.item_khuyenmai,khuyenMaiList);
            this.context=context;
            this.khuyenMaiList=khuyenMaiList;
        }


        private class ViewHolder {
            TextView ma;
            TextView phantram;
        }
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;
            if(convertView == null){
                LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row =layoutInflater.inflate(R.layout.item_khuyenmai,parent,false);
                holder = new ViewHolder();
                holder.ma=(TextView) row.findViewById(R.id.magiamgia);
                holder.phantram=(TextView) row.findViewById(R.id.phantram);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }

            KhuyenMai khuyenMai = khuyenMaiList.get(position);
            holder.ma.setText(khuyenMai.getKhuyenMaiId());
            holder.phantram.setText(khuyenMai.getPhantramgiam()+"");

            return row;
        }
    }

}
