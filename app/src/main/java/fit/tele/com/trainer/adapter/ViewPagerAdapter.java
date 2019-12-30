package fit.tele.com.trainer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 5;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);

//        mFragmentList.add(new SearchNearbyActivity());
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }



    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

}
