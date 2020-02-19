package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.model.DataItem;
import com.google.android.material.textview.MaterialTextView;

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


        holder.tvHourlyTemp.setText(String.format(Locale.getDefault(), "%.0f°", data.getTemperature()));
        holder.tvHourlyTime.setText(String.format(Locale.getDefault(), "%s", formattedDate));

        Glide.with(holder.ivIconRecycler.getContext())
                .load(setImageRes(data.getIcon()))
                .placeholder(R.drawable.cloudy)
                .into(holder.ivIconRecycler);
    }

    @Override
    public int getItemCount() {
        return hourlyData.size();
    }

    public void setHourly(List<DataItem> dataItem) {
        this.hourlyData = dataItem;
        notifyDataSetChanged();
    }

    private int setImageRes(String resName) {
        switch (resName) {
            case "clear-day":
                return R.drawable.clear_day;
            case "clear-night":
                return R.drawable.clear_night;
            case "partly-cloudy-day":
                return R.drawable.partly_cloudy_day;
            case "partly-cloudy-night":
                return R.drawable.partly_cloudy_night;
            case "rain":
                return R.drawable.rain;
            case "sleet":
                return R.drawable.sleet;
            case "snow":
                return R.drawable.snow;
            case "wind":
                return R.drawable.wind;
            case "cloudy":
                return R.drawable.cloudy;
            case "fog":
                return R.drawable.fog;
        }
        return 0;
    }

    class HourlyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvHourlyTemp;
        private MaterialTextView tvHourlyTime;
        private ImageView ivIconRecycler;

        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHourlyTemp = itemView.findViewById(R.id.tvHourlyTemp);
            tvHourlyTime = itemView.findViewById(R.id.tvHourlyTime);
            ivIconRecycler = itemView.findViewById(R.id.ivIconRecycler);
        }
    }
}
