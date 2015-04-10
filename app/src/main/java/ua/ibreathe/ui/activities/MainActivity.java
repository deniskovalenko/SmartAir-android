package ua.ibreathe.ui.activities;

import ua.ibreathe.R;
import ua.ibreathe.ui.fragments.DevicesListFragment;

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
