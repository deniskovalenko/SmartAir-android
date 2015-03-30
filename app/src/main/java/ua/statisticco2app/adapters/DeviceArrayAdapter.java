package ua.statisticco2app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import ua.statisticco2app.models.entities.Device;
import ua.statisticco2app.ui.holders.DeviceItemHolder;

public class DeviceArrayAdapter extends ArrayAdapter<Device> {
    DeviceItemHolder.ViewCreator creator;

    public DeviceArrayAdapter(Context context) {
        super(context, DeviceItemHolder.RES_ID, 0);
        creator = DeviceItemHolder.initCreator(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = creator.create(parent);
        DeviceItemHolder holder = DeviceItemHolder.retrieve(convertView, position);

        Device device = getItem(position);
        holder.getTvDeviceAlias().setText(device.getDevice_name());

        return convertView;
    }
}
