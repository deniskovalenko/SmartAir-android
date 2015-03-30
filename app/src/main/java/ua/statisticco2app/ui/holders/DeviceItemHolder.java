package ua.statisticco2app.ui.holders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.statisticco2app.R;

public class DeviceItemHolder extends ViewHolderBase {
    public static @LayoutRes
    int RES_ID = R.layout.item_devise_list;

    @InjectView(R.id.tvDeviceAlias)
    TextView tvDeviceAlias;

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
}
