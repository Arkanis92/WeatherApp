package com.example.weatherapp.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.adapter.HourlyAdapter;
import com.example.weatherapp.model.DataItem;
import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.viewmodel.WeatherViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private MaterialTextView tvStatus;
    private MaterialTextView tvTemperature;
    private MaterialTextView tvTempHigh;
    private MaterialTextView tvTempLow;
    private RecyclerView rvHourly;
    private HourlyAdapter adapter;
    private List<DataItem> hourlyData;


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvStatus = view.findViewById(R.id.tvStatus);
        tvTemperature = view.findViewById(R.id.tvTemperature);
        tvTempHigh = view.findViewById(R.id.tvTempHigh);
        tvTempLow = view.findViewById(R.id.tvTempLow);
        rvHourly = view.findViewById(R.id.rvHourly);

        hourlyData = new ArrayList<>();

        rvHourly.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new HourlyAdapter();
        rvHourly.setAdapter(adapter);

        weatherViewModel = new ViewModelProvider
                .AndroidViewModelFactory(requireActivity()
                .getApplication())
                .create(WeatherViewModel.class);

        setupObservers();
        weatherViewModel.getWeatherObservable();
    }

    private void setupObservers() {
        weatherViewModel.getWeatherLiveData().observe(Objects.requireNonNull(getActivity()), new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                if (weatherResponse != null) {
                    tvStatus.setText(String.format(Locale.getDefault(), "%s",
                            weatherResponse.getCurrently().getSummary()));
                    tvTemperature.setText(String.format(Locale.getDefault(), "%.0f°",
                            weatherResponse.getCurrently().getTemperature()));
                    tvTempHigh.setText(String.format(Locale.getDefault(), "%.0f°",
                            weatherResponse.getDaily().getData().get(0).getTemperatureHigh()));
                    tvTempLow.setText(String.format(Locale.getDefault(),"%.0f°",
                            weatherResponse.getDaily().getData().get(0).getTemperatureLow()));

                    hourlyData = weatherResponse.getHourly().getData();
                    adapter.setHourly(hourlyData);
                }
            }
        });

        weatherViewModel.getErrorLiveData().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
