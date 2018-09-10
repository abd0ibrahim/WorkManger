package com.khoroga.abdo.workmanager;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;

public class MyWorker extends Worker {


    @NonNull
    @Override
    public Result doWork() {

        Log.e("SCHEDULED TASK", String.valueOf(MainActivity.TIME));

        return Result.SUCCESS;
    }
}
