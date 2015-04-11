package com.smartair.app.ui.activities;

import com.smartair.app.R;
import com.smartair.app.ui.fragments.DevicesListFragment;

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
