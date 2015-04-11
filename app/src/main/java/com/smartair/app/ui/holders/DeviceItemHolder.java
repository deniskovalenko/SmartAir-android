package com.smartair.app.ui.holders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.smartair.app.R;

public class DeviceItemHolder extends ViewHolderBase {
    public static @LayoutRes
    int RES_ID = R.layout.item_devise_list;

    @InjectView(R.id.tvDeviceAlias)
    TextView tvDeviceAlias;

    @InjectView(R.id.tvCO2)
    TextView tvCO2;

    @InjectView(R.id.tvCO2Changed)
    TextView tvCO2Changed;

    @InjectView(R.id.tvTemperature)
    TextView tvTemperature;

    @InjectView(R.id.tvTemperatureChanged)
    TextView tvTemperatureChanged;

    @InjectView(R.id.tvHumidity)
    TextView tvHumidity;

    @InjectView(R.id.tvHumidityChanged)
    TextView tvHumidityChanged;

    protected DeviceItemHolder(@NotNull View v) {
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
                return new DeviceItemHolder(view);
            }
        };
    }

    public TextView getTvDeviceAlias() {
        return tvDeviceAlias;
    }

    public TextView getTvCO2() {
        return tvCO2;
    }

    public TextView getTvCO2Changed() {
        return tvCO2Changed;
    }

    public TextView getTvTemperature() {
        return tvTemperature;
    }

    public TextView getTvTemperatureChanged() {
        return tvTemperatureChanged;
    }

    public TextView getTvHumidity() {
        return tvHumidity;
    }

    public TextView getTvHumidityChanged() {
        return tvHumidityChanged;
    }
}
