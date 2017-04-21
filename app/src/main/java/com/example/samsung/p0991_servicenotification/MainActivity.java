package com.example.samsung.p0991_servicenotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String FILE_NAME = "filename";

    private TextView tvResult;
    private Intent intent;
    private String filrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);

        intent = getIntent();

        filrName = intent.getStringExtra(FILE_NAME);

        if (!TextUtils.isEmpty(filrName)) {
            tvResult.setText(filrName);
        }
    }

    public void onClickBtn(View view) {

        switch (view.getId()) {

            case R.id.btnStart :
                startService(new Intent(this, MyService.class));
                break;
            case R.id.btnStop :
                stopService(new Intent(this, MyService.class));
                break;
            default:
                break;
        }

    }
}
