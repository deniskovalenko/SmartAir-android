package ua.statisticco2app.ui.activities;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import org.jetbrains.annotations.NotNull;

public abstract class BaseActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        findView();
        onViewCreated();
        setListeners();
    }

    protected abstract @LayoutRes int getLayoutId();
    protected void findView() {}
    protected void onViewCreated() {}
    protected void setListeners() {}
    protected abstract @IdRes int getFragmentContainerId();

    protected void updateFragment(@NotNull Fragment fragment, boolean saveInBackStack, boolean add,
                                  @AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {

        final String fragmentTag = ((Object) fragment).getClass().getName();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (popEnter != 0)
            ft.setCustomAnimations(enter, exit, popEnter, popExit);
        else if (enter != 0)
            ft.setCustomAnimations(enter, exit);

        if (add) {
            ft.add(getFragmentContainerId(), fragment, fragmentTag);
        } else {
            ft.replace(getFragmentContainerId(), fragment, fragmentTag);
        }

        if (saveInBackStack) {
            ft.addToBackStack(fragmentTag);
        }
        ft.commit();
    }

    protected void updateFragment(@NotNull Fragment fragment, boolean saveInBackStack, boolean add) {
        updateFragment(fragment, saveInBackStack, add, 0, 0, 0, 0);
    }

    protected void updateFragment(@NotNull Fragment fragment, boolean saveInBackStack, boolean add,
                                  @AnimRes int enter, @AnimRes int exit) {
        updateFragment(fragment, saveInBackStack, add, enter, exit, 0, 0);
    }

    public void switchFragment(@NotNull Fragment fragment, boolean saveInBackStack) {
        updateFragment(fragment, saveInBackStack, false);
    }

    public void switchFragment(@NotNull Fragment fragment, boolean saveInBackStack, @AnimRes int enter, @AnimRes int exit) {
        updateFragment(fragment, saveInBackStack, false, enter, exit);
    }

    public void switchFragment(@NotNull Fragment fragment, boolean saveInBackStack,
                               @AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        updateFragment(fragment, saveInBackStack, false, enter, exit, popEnter, popExit);
    }

    public void addFragment(@NotNull Fragment fragment, boolean saveInBackStack) {
        updateFragment(fragment, saveInBackStack, true);
    }

    public void addFragment(@NotNull Fragment fragment, boolean saveInBackStack, @AnimRes int enter, @AnimRes int exit) {
        updateFragment(fragment, saveInBackStack, true, enter, exit);
    }

    public void addFragment(@NotNull Fragment fragment, boolean saveInBackStack,
                            @AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        updateFragment(fragment, saveInBackStack, true, enter, exit, popEnter, popExit);
    }
}
