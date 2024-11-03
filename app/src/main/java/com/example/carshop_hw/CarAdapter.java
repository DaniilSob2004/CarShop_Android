package com.example.carshop_hw;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carshop_hw.model.CarModel;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends ArrayAdapter<CarModel> {

    private LayoutInflater inflater;
    private int layout;
    private List<CarModel> carsModel;

    public CarAdapter(Context context, int resource, List<CarModel> carsModel) {
        super(context, resource, carsModel);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.carsModel = carsModel;
    }

    public void updateCarModels(List<CarModel> newCarModels) {
        this.carsModel.clear();
        this.carsModel.addAll(newCarModels);
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CarModel carModel = carsModel.get(position);

        viewHolder.photoView.setImageResource(carModel.getIdPhoto());
        viewHolder.brandView.setText(carModel.getBrand());
        viewHolder.modelView.setText(carModel.getModel());
        viewHolder.yearView.setText("(" + carModel.getYear() + ")");
        viewHolder.descriptionView.setText(carModel.getDescription());
        viewHolder.costView.setText(String.valueOf(carModel.getCost()));

        return convertView;
    }

    private class ViewHolder {

        final ImageView photoView;
        final TextView brandView;
        final TextView modelView;
        final TextView yearView;
        final TextView descriptionView;
        final TextView costView;

        ViewHolder(View view) {
            photoView = view.findViewById(R.id.photo);
            brandView = view.findViewById(R.id.brand);
            modelView = view.findViewById(R.id.model);
            yearView = view.findViewById(R.id.year);
            descriptionView = view.findViewById(R.id.description);
            costView = view.findViewById(R.id.cost);
        }
    }
}
