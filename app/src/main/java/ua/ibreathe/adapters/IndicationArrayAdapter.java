package ua.ibreathe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import ua.ibreathe.models.entities.Indication;
import ua.ibreathe.ui.holders.IndicationItemHolder;

public class IndicationArrayAdapter extends ArrayAdapter<Indication> {
    IndicationItemHolder.ViewCreator viewCreator;

    public IndicationArrayAdapter(Context context) {
        super(context, IndicationItemHolder.RES_ID, 0);
        viewCreator = IndicationItemHolder.initCreator(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = viewCreator.create(parent);
        IndicationItemHolder holder = IndicationItemHolder.retrieve(convertView, position);

        Indication indication = getItem(position);
        holder.getTvTemperature().setText(String.valueOf(indication.getTemperature()));
        holder.getTvDate().setText(String.valueOf(indication.getDate()));

        return convertView;
    }
}
