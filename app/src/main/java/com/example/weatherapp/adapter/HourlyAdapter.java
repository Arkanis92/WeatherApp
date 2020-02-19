package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.DataItem;
import com.example.weatherapp.model.Hourly;
import com.example.weatherapp.model.WeatherResponse;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder> {

    private List<DataItem> hourlyData;

    public HourlyAdapter() {
        this.hourlyData = new ArrayList<>();
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_item, parent, false);
        return new HourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        DataItem data = hourlyData.get(position);

        Date time = new Date(data.getTime() * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("h:00 aa", Locale.getDefault());
        String formattedDate = sdf.format(time);

        holder.tvHourlyTemp.setText(String.format(Locale.getDefault(),"%.0f°", data.getTemperature()));
        holder.tvHourlyTime.setText(String.format(Locale.getDefault(),"%s", formattedDate));
    }

    @Override
    public int getItemCount() {
        return hourlyData.size();
    }

    public void setHourly(List<DataItem> dataItem) {
        this.hourlyData = dataItem;
        notifyDataSetChanged();
    }

    class HourlyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvHourlyTemp;
        private MaterialTextView tvHourlyTime;

        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHourlyTemp = itemView.findViewById(R.id.tvHourlyTemp);
            tvHourlyTime = itemView.findViewById(R.id.tvHourlyTime);
        }
    }
}
