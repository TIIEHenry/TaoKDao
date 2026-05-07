package tiiehenry.taokdao.ui.view.tablayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * A {@link OnTabSelectedListener} class which contains the necessary calls back to the
 * provided {@link ViewPager} so that the tab position is kept in sync.
 */
public class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
    private final ViewPager viewPager;

    public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(@NonNull TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        // No-op
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // No-op
    }
}
