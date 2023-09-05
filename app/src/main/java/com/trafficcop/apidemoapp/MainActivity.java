package com.trafficcop.apidemoapp;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    Button btnCallApi;
    TextView tvIvtScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallApi = findViewById(R.id.btnCallApi);
        tvIvtScore = findViewById(R.id.tvValueIvtScore);

        btnCallApi.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }

    private String getLanguage() {
        Resources resources = getApplication().getResources();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return resources.getConfiguration().getLocales().get(0).getLanguage();
        } else {
            return resources.getConfiguration().locale.getLanguage();
        }
    }

    private Long getTimeZoneOffset() {
        // get current date time
        Long date = System.currentTimeMillis();

        return -TimeUnit.MILLISECONDS.toMinutes(TimeZone.getDefault().getOffset(date));
    }

    private String getApplicationId() {
        return "com.trafficcop.apidemoapp";
    }

    private void onButtonClick() {
        // Create an instance of ApiService
        ApiService apiService = ApiClient.getApiService();

        // Create an instance of TrafficCopRequestBody
        TrafficCopRequestBody request = new TrafficCopRequestBody();
        request.setAppId(getApplicationId());
        request.setNavigatorLanguage(getLanguage());
        request.setTimezoneOffset(getTimeZoneOffset());

        try {
            Call<TrafficCopResponse> call = apiService.checkIvtScore(request);

            call.enqueue(new Callback<TrafficCopResponse>() {
                @Override
                public void onResponse(Call<TrafficCopResponse> call, Response<TrafficCopResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        double result = response.body().getIvtScore();
                        // Handle the API response here
                        tvIvtScore.setText(String.valueOf(result));
                    } else {
                        // Handle API error
                        Log.e(TAG, "Traffic Cop API call not successful.");
                    }
                }

                @Override
                public void onFailure(Call<TrafficCopResponse> call, Throwable t) {
                    // Handle network or other errors
                    Log.e(TAG, "Traffic Cop API call failed.");
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "Exception occurred");
            e.printStackTrace();
        }
    }

}

