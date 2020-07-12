//package com.foodorderingapp.activity.client.myordersfragment;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.foodorderingapp.R;
//import com.foodorderingapp.activity.client.myordersfragment.donhangdanggiaofragment.DonHangDangGiaoFragment;
//import com.foodorderingapp.activity.client.myordersfragment.donhanghuyfragment.DonHangHuyFragment;
//import com.foodorderingapp.activity.client.myordersfragment.lichsudonhangfragment.LichSuDonHangFragment;
//import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyOrdersFragement extends Fragment {
//    private String taiKhoanId;
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//    public MyOrdersFragement(String taiKhoanId) {
//        this.taiKhoanId=taiKhoanId;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        System.out.println("Đã vào hàm OnCreateView");
////        return inflater.inflate(R.layout.myorders_fragment,container,false);
//
//        View view = inflater.inflate(R.layout.myorders_fragment,container, false);
//        // Setting ViewPager for each Tabs
//        ViewPager viewPager = view.findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//        // Set Tabs inside Toolbar
//        TabLayout tabs = view.findViewById(R.id.result_tabs);
//        tabs.setupWithViewPager(viewPager);
//        return view;
//    }
//
//    // Add Fragments to Tabs
//    private void setupViewPager(ViewPager viewPager) {
//        Adapter adapter = new Adapter(getChildFragmentManager());
//        adapter.addFragment(new DonHangHuyFragment(taiKhoanId), "Đơn hàng hủy");
//        adapter.addFragment(new DonHangDangGiaoFragment(taiKhoanId), "Đơn hàng đang giao");
//        adapter.addFragment(new LichSuDonHangFragment(taiKhoanId), "Lịch sử đơn hàng");
//        viewPager.setAdapter(adapter);
//    }
//
//    static class Adapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public Adapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }
//}
//
