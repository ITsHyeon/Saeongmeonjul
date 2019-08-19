package com.example.saeongmeonjul;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saeongmeonjul.adapter.PagerAdapter;
import com.example.saeongmeonjul.fragment.InventoryManagmentFragment;
import com.example.saeongmeonjul.fragment.PublicToiletFragment;
import com.example.saeongmeonjul.fragment.RegistrationManagmentFragment;
import com.example.saeongmeonjul.fragment.TrailFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private Context mContext;

    public static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewpager);
        mTab = findViewById(R.id.tabs);
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mContext = getApplicationContext();


       // Toolbar Setting
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

//        // Adapter Fragment Setting
//        mPagerAdapter.addFragment(R.drawable.tab_public_toilet, "주변 공중화장실", new PublicToiletFragment());
//        mPagerAdapter.addFragment(R.drawable.tab_trail, "산책로 추천", new TrailFragment());
//        mPagerAdapter.addFragment(R.drawable.tab_registration_managment, "동물등록증 관리", new RegistrationManagmentFragment());
//        mPagerAdapter.addFragment(R.drawable.tab_inventory_managment, "재고 관리", new InventoryManagmentFragment());

        // TabLayout Setting
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_public_toilet, "주변 공중화장실")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_trail, "산책로 추천")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_registration_managment, "동물등록증 관리")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_inventory_managment, "재고관리")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_trail, "블루투스")));
//        mTab.setupWithViewPager(mViewPager);

        // ViewPager Setting
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));

//        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
//            mTab.getTabAt(i).setIcon(mPagerAdapter.getFragmentInfo(i).getIconResId());
//        }

        mTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                pos = tab.getPosition();
//
//                switch (pos) {
//                    case 0:
//                        mTab.getTabAt(0).setIcon(R.drawable.tab_public_toilet);
//                        break;
//                    case 1:
//                        mTab.getTabAt(1).setIcon(R.drawable.tab_trail);
//                        break;
//                    case 2:
//                        mTab.getTabAt(2).setIcon(R.drawable.tab_registration_managment);
//                        break;
//                    case 3:
//                        mTab.getTabAt(3).setIcon(R.drawable.tab_inventory_managment);
//                        break;
//                }
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View createTabView(int iconResId, String tabName){
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        ImageView icon = tabView.findViewById(R.id.tab_icon);
        TextView title = tabView.findViewById(R.id.tab_text);
        icon.setImageResource(iconResId);
        title.setText(tabName);
        return tabView;

    }

}
