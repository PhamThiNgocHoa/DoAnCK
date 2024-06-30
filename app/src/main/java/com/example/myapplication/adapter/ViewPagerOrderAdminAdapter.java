package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.OrderHandleAdminFragment;
import com.example.myapplication.OrderSuccessAdminFragment;

public class ViewPagerOrderAdminAdapter extends FragmentStatePagerAdapter {

    public ViewPagerOrderAdminAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrderHandleAdminFragment();
            case 1:
                return new OrderSuccessAdminFragment();
            default:
                return new OrderHandleAdminFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Handle Order";
                break;
            case 1:
                title = "Success Order";
                break;
        }
        return title;
    }
}
