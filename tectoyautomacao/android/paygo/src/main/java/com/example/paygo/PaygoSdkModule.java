package com.example.paygo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import br.com.setis.interfaceautomacao.Confirmacoes;
import br.com.setis.interfaceautomacao.DadosAutomacao;
import br.com.setis.interfaceautomacao.EntradaTransacao;
import br.com.setis.interfaceautomacao.Personalizacao;
import br.com.setis.interfaceautomacao.SaidaTransacao;
import br.com.setis.interfaceautomacao.Transacoes;

public class PaygoSdkModule extends ReactContextBaseJavaModule implements ActivityEventListener {


    private Confirmacoes mConfirmacao = new Confirmacoes();
    private DadosAutomacao mDadosAutomacao = null;
    private Personalizacao mPersonalizacao;
    private Transacoes mTransacoes = null;
    private SaidaTransacao mSaidaTransacao;
    private EntradaTransacao mEntradaTransacao;
    private String versoes;
    private static String mensagem = null;
   // private static Handler mHandler = new Handler();
    private String nsu, dataOperacao, codigoAutorizacao, valorOperacao;

    private static int REQUEST_CODE = 1000;

    private static ReactApplicationContext reactContext;
    PaygoSdkModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
        reactContext.addActivityEventListener(this);

    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @NonNull
    @Override
    public String getName() {
        return "PaygoSdkModule";
    }

    @ReactMethod
    public void Pagar(){

    }

    @ReactMethod
    public void Administrativo(){
        
    }
}
