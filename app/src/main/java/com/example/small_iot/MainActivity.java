package com.example.small_iot;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.small_iot.Model.DataIn;
import com.example.small_iot.Model.DataOut;
import com.example.small_iot.Service.apiClient;
import com.example.small_iot.Service.getService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView dat_sen;
    private Button btnAction;
    private String stateBtn;

    private String idOut, namaOut, valueOut;

    Handler mHandler;
    getService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dat_sen = (TextView)findViewById(R.id.dat_sensor);
        btnAction = (Button)findViewById(R.id.btnAction);

        service = apiClient.getRetrofitInstance().create(getService.class);

        this.mHandler = new Handler();
        m_Runnable.run();

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<DataOut>> putAction = service.putOut(idOut, namaOut, valueOut);
                putAction.enqueue(new Callback<List<DataOut>>() {
                    @Override
                    public void onResponse(Call<List<DataOut>> call, Response<List<DataOut>> response) {
                        Toast.makeText(MainActivity.this, "Success send data", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<DataOut>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Can't send data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            refreshDataIn();
            refreshDataOut();
            MainActivity.this.mHandler.postDelayed(m_Runnable,5000);
        }

    };

    private void refreshDataIn(){
        Call<List<DataIn>> call = service.getDataIn();
        call.enqueue(new Callback<List<DataIn>>() {
            @Override
            public void onResponse(Call<List<DataIn>> call, Response<List<DataIn>> response) {
                List<DataIn> result = response.body();
                dat_sen.setText(result.get(0).getValue_data());

            }

            @Override
            public void onFailure(Call<List<DataIn>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshDataOut(){
        Call<List<DataOut>> callOut = service.getDataOut();
        callOut.enqueue(new Callback<List<DataOut>>() {
            @Override
            public void onResponse(Call<List<DataOut>> call, Response<List<DataOut>> response) {
                List<DataOut> hasil = response.body();
                idOut = hasil.get(0).getId_data();
                namaOut = hasil.get(0).getNama_data();
                valueOut = hasil.get(0).getValue_data();
                if (valueOut.equals("0")){
                    btnAction.setText(" Button OFF");
                    valueOut = "1";
                }else if (valueOut.equals("1")){
                    btnAction.setText("Button On");
                    valueOut = "0";
                }
            }

            @Override
            public void onFailure(Call<List<DataOut>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void setStateBtn(String state){
//        if (state.equals("0")){
//            btnAction.set
//        }
//    }
}
