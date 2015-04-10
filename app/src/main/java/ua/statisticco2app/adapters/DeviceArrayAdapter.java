package ua.statisticco2app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import ua.statisticco2app.R;
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
        holder.getTvDeviceAlias().setText(device.getDeviceName());

        holder.getTvCO2().setText(formatString(R.string.current_co2, device.getCurrentCO2()));
        int deltaCO2 = device.getDeltaCO2();
        holder.getTvCO2Changed().setText(formatString(R.string.change_co2, deltaCO2));
        holder.getTvCO2Changed().setTextColor(getColor(deltaCO2, R.color.increase_co2, R.color.decrease_co2));

        holder.getTvHumidity().setText(formatString(R.string.current_humidity, device.getCurrentHumidity()));
        float deltaHumidity = device.getDeltaHumidity();
        holder.getTvHumidityChanged().setText(formatString(R.string.change_humidity, deltaHumidity));
        holder.getTvHumidityChanged().setTextColor(getColor(deltaHumidity, R.color.increase_humidity, R.color.decrease_humidity));

        holder.getTvTemperature().setText(formatString(R.string.current_temperature, device.getCurrentTemperature()));
        float deltaTemp = device.getDeltaTemperature();
        holder.getTvTemperatureChanged().setTextColor(getColor(deltaTemp, R.color.increase_temperature, R.color.decrease_temperature));
        holder.getTvTemperatureChanged().setText(formatString(R.string.change_temperature, deltaTemp));
        return convertView;
    }

    private String formatString(int id, Object... format){
        return getContext().getString(id, format);
    }

    private int getColor(float values, int inc, int dec) {
        return  getContext().getResources().getColor(values == 0.0f ? R.color.no_change : (values < 0 ? dec : inc));
    }
}
