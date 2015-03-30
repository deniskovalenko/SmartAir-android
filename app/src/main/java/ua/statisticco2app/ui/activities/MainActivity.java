package ua.statisticco2app.ui.activities;

import ua.statisticco2app.R;
import ua.statisticco2app.ui.fragments.DevicesListFragment;
import ua.statisticco2app.ui.fragments.SimpleFragment;

public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected void onViewCreated() {
        switchFragment(DevicesListFragment.getInstance(), false);
    }
}
