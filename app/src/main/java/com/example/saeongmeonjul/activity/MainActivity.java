package com.example.saeongmeonjul.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saeongmeonjul.R;
import com.example.saeongmeonjul.adapter.PagerAdapter;
import com.example.saeongmeonjul.fragment.InventoryManagmentFragment;
import com.example.saeongmeonjul.fragment.PublicToiletFragment;
import com.example.saeongmeonjul.fragment.RegistrationManagmentFragment;
import com.example.saeongmeonjul.fragment.TrailFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private Context mContext;

    public static int pos;
    private boolean enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 카카오 맵 해시키
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String key = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key : ", key );
            }
        } catch (Exception e){
            Log.e("name not found", e.toString());
        }
        // init
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewpager);
        mTab = findViewById(R.id.tabs);
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mContext = getApplicationContext();


       // Toolbar Setting
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        // TabLayout Setting
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_public_toilet, "주변 공중화장실")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_trail, "산책로 추천")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_registration_managment, "동물등록증 관리")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_inventory_managment, "재고관리")));
        mTab.addTab(mTab.newTab().setCustomView(createTabView(R.drawable.tab_trail, "블루투스")));

        // ViewPager Setting
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));

        mTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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

    public void setPagingEnabled(boolean enabled){
        this.enabled = enabled;
    }

}
