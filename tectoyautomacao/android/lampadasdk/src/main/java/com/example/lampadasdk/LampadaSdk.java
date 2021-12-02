package com.example.lampadasdk;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.sunmi.statuslampmanager.IStateLamp;

public class LampadaSdk {

    private IStateLamp mService;

    private static LampadaSdk helper = new LampadaSdk();

    private LampadaSdk() {}

    public static LampadaSdk getInstance() {
        return helper;
    }


}
