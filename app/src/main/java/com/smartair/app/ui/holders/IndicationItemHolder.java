package com.smartair.app.ui.holders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.smartair.app.R;

@Deprecated
public class IndicationItemHolder extends ViewHolderBase {
    public static @LayoutRes int RES_ID = R.layout.item_indication;
    @InjectView(R.id.tvDate)
    TextView tvDate;
    @InjectView(R.id.tvTemperature)
    TextView tvTemperature;

    protected IndicationItemHolder(@NotNull View v) {
        super(v);
    }

    @Override
    protected void initHolder(@NotNull View v) {
        ButterKnife.inject(this, v);
    }

    public static ViewCreator initCreator(Context context) {
        return new ViewCreator(context) {

            @Override
            protected int getHolderViewResourceId() {
                return RES_ID;
            }

            @Override
            protected ViewHolderBase getNewHolderInstance(@NotNull View view) {
                return new IndicationItemHolder(view);
            }
        };
    }

    public TextView getTvTemperature() {
        return tvTemperature;
    }

    public TextView getTvDate() {
        return tvDate;
    }
}
