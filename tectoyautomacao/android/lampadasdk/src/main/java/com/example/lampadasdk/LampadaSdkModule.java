package com.example.lampadasdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.sunmi.statuslampmanager.IStateLamp;


public class LampadaSdkModule extends ReactContextBaseJavaModule {



    private IStateLamp mService;

    LampadaSdkModule(ReactApplicationContext context) {
        super(context);
        connectService();
    }

    @NonNull
    @Override
    public String getName() {
        return "LampadaSdkModule";
    }

    @ReactMethod
    public void luzAzul() throws RemoteException {
        mService.closeAllLamp();
        mService.controlLamp(0, "Led-3");
    }

    @ReactMethod
    public void luzAmarela() throws RemoteException {
        mService.closeAllLamp();
        mService.controlLamp(0, "Led-2");
        mService.controlLampForLoop(0,50000,100,"Led-1");
    }

    @ReactMethod
    public void luzRoxa() throws RemoteException {
        mService.controlLamp(0, "Led-3");
        mService.controlLampForLoop(0,500000,100,"Led-1");
    }

    @ReactMethod
    public void luzAzulClaro() throws RemoteException {
        mService.closeAllLamp();
        mService.controlLamp(0, "Led-3");
        mService.controlLampForLoop(0,500000,100,"Led-2");
    }
    @ReactMethod
    public void luzVerde() throws RemoteException {
        System.out.println("luzzz");
        mService.closeAllLamp();
        mService.controlLamp(0, "Led-2");
    }

    @ReactMethod
    public void luzVermelha() throws RemoteException {
        mService.closeAllLamp();
        mService.controlLamp(0, "Led-1");
    }
    @ReactMethod
    public void desligar() throws RemoteException {
        mService.closeAllLamp();
    }
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IStateLamp.Stub.asInterface(service);
            Log.d("darren", "Service Connected.");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("darren", "Service Disconnected.");
            mService = null;
        }
    };
    private void connectService() {
        Intent intent = new Intent();
        intent.setPackage("com.sunmi.statuslampmanager");
        intent.setAction("com.sunmi.statuslamp.service");
        getReactApplicationContext().startService(intent);
        getReactApplicationContext().bindService(intent, con, Context.BIND_AUTO_CREATE);
    }
}
