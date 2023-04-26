package com.itpharma;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import br.com.itfast.tectoy.Dispositivo;
import br.com.itfast.tectoy.TecToy;
import br.com.itfast.tectoy.TecToyNfcCallback;
//import br.com.itfast.tectoy.Dispositivo;
//import br.com.itfast.tectoy.TecToy;

public class It4rModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private TecToy tecToy;
    private PendingIntent pendingIntent;
    private Intent myIntent;

    TecToyNfcCallback callback = new TecToyNfcCallback() {
        @Override
        public void retornarValor(String strValor) {
            Log.i("TESTE", strValor);
        }
    };

    It4rModule(ReactApplicationContext context) {
        super(context);
        tecToy = new TecToy(Dispositivo.V2_PRO, context);

        //pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, context.getClass()) .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        try{

            tecToy.iniciarNFC(myIntent, callback);
        }catch (Exception e){
            Log.i("TESTE...", e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "It4rModule";
    }

    @ReactMethod
    public void printEvent(String txt) {
        tecToy.imprimir(txt);
    }

    @ReactMethod
    public void getNfc() {
        Log.i("TESTE", "Chamar NFC" );
        try {
            tecToy.iniciarNFC(this.getCurrentActivity().getIntent(), callback);
        }catch (Exception e){
            Log.i("TESTE", e.getMessage() );
        }
    }

    @Override
    public void onActivityResult(Activity activity, int i, int i1, @Nullable Intent intent) {

    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.i("TESTE", "no new intent");
        if(intent != null){
            tecToy.onNewIntentNFC(intent);
        }

    }

}
