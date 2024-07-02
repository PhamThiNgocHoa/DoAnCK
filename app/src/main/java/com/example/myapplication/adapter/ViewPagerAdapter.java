package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.CompletedFragment;
import com.example.myapplication.ProcessingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ProcessingFragment(); // Đang xử lý
            case 1:
                return new CompletedFragment(); // Hoàn thành
            default:
                return new ProcessingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
