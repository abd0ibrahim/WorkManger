package com.khoroga.abdo.workmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "UNIQUE-TAG";
    static Integer TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn);
        final EditText editText = (EditText) findViewById(R.id.txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("SCHEDULED TASK", "Clicked");
                TIME = Integer.parseInt((editText.getText().toString()));
                WorkManager.getInstance().cancelAllWorkByTag(TAG);

                OneTimeWorkRequest.Builder photoCheckBuilder = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setInitialDelay(
                                Long.parseLong(editText.getText().toString()), TimeUnit.SECONDS)
                        .addTag(TAG);

                /*PeriodicWorkRequest.Builder photoCheckBuilder = new PeriodicWorkRequest.Builder(
                        MyWorker.class,
                        20,
                        TimeUnit.SECONDS)
                        .addTag(TAG);*/
                OneTimeWorkRequest photoCheckWork = photoCheckBuilder.build();

                WorkManager.getInstance().enqueue(photoCheckWork);
            }
        });


    }

}
