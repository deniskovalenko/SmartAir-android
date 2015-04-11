package com.smartair.app.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import butterknife.InjectView;
import com.smartair.app.R;

public abstract class BaseRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void setListeners() {
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    protected void setRefreshing(boolean refresh) {
        swipeRefreshLayout.setRefreshing(refresh);
    }

    @Override
    protected void onViewCreated() {
        ((ActionBarActivity)getActivity()).setSupportActionBar(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }
}