package android.com.phisicsapp.adapter;

import android.com.phisicsapp.R;
import android.com.phisicsapp.model.ISpinner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter<T extends ISpinner> extends android.widget.ArrayAdapter<T> {
    private ArrayList<T> items;

    public SpinnerAdapter(Context context, ArrayList<T> items) {
        super(context, -1, items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, parent, false);
        textView.setText(items.get(position).getName());
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, parent, false);
        textView.setText(items.get(position).getName());
        return textView;
    }
}
