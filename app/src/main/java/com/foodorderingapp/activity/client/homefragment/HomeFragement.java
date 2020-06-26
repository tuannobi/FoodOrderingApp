package com.foodorderingapp.activity.client.homefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.foodorderingapp.R;
import com.foodorderingapp.adapter.SanPhamAdapter;
import com.foodorderingapp.dialog.DialogItem;
import com.foodorderingapp.model.SanPham;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragement extends Fragment {
    SearchView searchView;
    GridView gridView;
    ArrayList<SanPham> arraySanPham;
    SanPhamAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        AnhXa(view);
        adapter = new SanPhamAdapter(this,R.layout.item,arraySanPham);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                SanPham sanPham = arraySanPham.get(position);
                System.out.println(sanPham.getTenSanPham());
                bundle.putSerializable("key", (Serializable) sanPham);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                DialogItem f = new DialogItem();
                f.setArguments(bundle);
                f.show(ft, "dialog");




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


        return view;
    }


    public void AnhXa(View view){
        searchView = (SearchView)view.findViewById(R.id.searchview);
        gridView = (GridView) view.findViewById(R.id.gridview);
        arraySanPham = new ArrayList<SanPham>();
        db.collection("SanPham")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            System.err.println("Listen failed:" + e);
                            return;
                        }
                        List<SanPham> sanPhams = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            sanPhams.add(doc.toObject(SanPham.class));
                        }
                        System.out.println(sanPhams.toString());
                        for (SanPham sanPham:sanPhams){
                            arraySanPham.add(new SanPham(sanPham.getSanPhamId(),sanPham.getTenSanPham(),sanPham.getGiaGoc(),sanPham.getGiaBanLe(),sanPham.getKho(),sanPham.getTrangThai(),sanPham.getPhanLoaiId(),sanPham.getHinhAnhId(),sanPham.getMoTa()));
                        }
                    }
                });


    }






}
