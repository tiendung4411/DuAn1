package com.example.duan1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1.fragment.CoffeeFragment;
import com.example.duan1.fragment.DessertFragment;
import com.example.duan1.fragment.JuiceFragment;
import com.example.duan1.fragment.MilkTeaFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager,  lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CoffeeFragment();
            case 1:
                return new JuiceFragment();
            case 2:
                return new MilkTeaFragment();
            case 3:
                return new DessertFragment();
            default:
                return new CoffeeFragment();

        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public int getCount() {
        return 3 ;
    }
}
