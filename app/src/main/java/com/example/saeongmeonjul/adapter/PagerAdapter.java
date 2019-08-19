package com.example.saeongmeonjul.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.saeongmeonjul.fragment.BluetoothFragment;
import com.example.saeongmeonjul.fragment.FragmentInfo;
import com.example.saeongmeonjul.fragment.InventoryManagmentFragment;
import com.example.saeongmeonjul.fragment.PublicToiletFragment;
import com.example.saeongmeonjul.fragment.RegistrationManagmentFragment;
import com.example.saeongmeonjul.fragment.TrailFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
//    private final List<FragmentInfo> mFragmentList= new ArrayList<>();

    private static int PAGE_NUMBER = 5;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PublicToiletFragment publicToiletFragment = new PublicToiletFragment();
                return  publicToiletFragment;
            case 1:
                TrailFragment trailFragment = new TrailFragment();
                return trailFragment;
            case 2:
                RegistrationManagmentFragment registrationManagmentFragment = new RegistrationManagmentFragment();
                return registrationManagmentFragment;
            case 3:
                InventoryManagmentFragment inventoryManagmentFragment = new InventoryManagmentFragment();
                return inventoryManagmentFragment;
            case 4:
                BluetoothFragment bluetoothFragment = new BluetoothFragment();
                return bluetoothFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }


}
