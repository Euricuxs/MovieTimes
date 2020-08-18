package com.example.movietimesuas.Viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.movietimesuas.Adapters.AdapterSavedMovie;
import com.example.movietimesuas.Fragments.SavedMovieTab;
import com.example.movietimesuas.Fragments.SearchMovieTab;

public class Pager extends FragmentStatePagerAdapter
{

    final int tabCount = 2;
    private String[] tabTitle = new String[]{"Search Movie","Saved Movie"};
    AdapterSavedMovie adapterSavedMovie;

    public Pager(@NonNull FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new SearchMovieTab();
            case 1:
                return new SavedMovieTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitle[position];
    }

    @Override
    public int getItemPosition(@NonNull Object object)
    {
        return POSITION_NONE;
    }

}
