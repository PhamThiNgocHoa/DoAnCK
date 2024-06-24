package com.example.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.MonthlyRevenueResponse;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatisticalActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private CombinedChart combinedChart;
    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical_admin);

        combinedChart = findViewById(R.id.combinedChart);
        combinedChart.getDescription().setEnabled(false);
        combinedChart.setBackgroundColor(Color.WHITE);
        combinedChart.setDrawGridBackground(false);
        combinedChart.setDrawBarShadow(false);
        combinedChart.setHighlightFullBarEnabled(false);

        YAxis rightAxis = combinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = combinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");


        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        // Fetch monthly revenue data
        fetchMonthlyRevenue();
    }

    private void fetchMonthlyRevenue() {
        orderService = RetrofitClient.getOrderService();
        orderService.getMonthlyRevenue().enqueue(new Callback<List<MonthlyRevenueResponse>>() {
            @Override
            public void onResponse(Call<List<MonthlyRevenueResponse>> call, Response<List<MonthlyRevenueResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MonthlyRevenueResponse> revenueResponse = response.body();
                    CombinedData combinedData = setupChartData(revenueResponse);
                    combinedChart.setData(combinedData);
                    combinedChart.invalidate();
                } else {
                    Log.e("Không tìm thấy danh sách doanh thu", "Không tìm thấy danh sách doanh thu");
                }
            }

            @Override
            public void onFailure(Call<List<MonthlyRevenueResponse>> call, Throwable throwable) {
                Log.e("Không gọi được api", "Không gọi được api");
            }
        });
    }

    private CombinedData setupChartData(List<MonthlyRevenueResponse> revenueResponse) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < revenueResponse.size(); i++) {
            entries.add(new Entry(i, revenueResponse.get(i).getRevenue() / 1000000f));
        }

        LineDataSet set = new LineDataSet(entries, "Doanh thu tháng");
        LineData lineData = new LineData(set);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);

        return combinedData;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        // Handle value selection if needed
    }

    @Override
    public void onNothingSelected() {
        // Handle nothing selected if needed
    }
}



