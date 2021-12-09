package com.example.msitef;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;

import br.com.tectoyautomacao.tectoysunmisdk.KTectoySunmiPrinter;
import br.com.tectoyautomacao.tectoysunmisdk.TectoySunmiPrint;

public class MsitefSdkModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static ReactApplicationContext reactContext;
    private static int REQ_CODE = 4321;
    private Random r = new Random();
    private Date dt = new Date();
    private String op = String.valueOf(r.nextInt(99999));
    KTectoySunmiPrinter kPrinterPresenter;

    public static String acao = "venda";

    Gson gson = new Gson();

    MsitefSdkModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
        reactContext.addActivityEventListener(this);

    }
    @NonNull
    @Override
    public String getName() {
        return "MsitefSdkModule";
    }
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        super.getCurrentActivity().onActivityReenter(requestCode, data);
        RetornoMsiTef retornoSitef = null;
        if (data == null)
            return;

        try {
            retornoSitef = gson.fromJson(respSitefToJson(data), RetornoMsiTef.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (requestCode == REQ_CODE && resultCode == getCurrentActivity().RESULT_OK) {
            if (retornoSitef.getCodResp().equals("0")) {
                String impressao = "";
                // Verifica se tem algo pra imprimir
                if (!retornoSitef.textoImpressoCliente().isEmpty()) {
                    impressao += retornoSitef.textoImpressoCliente();
                }
                if (!retornoSitef.textoImpressoEstabelecimento().isEmpty()) {
                    impressao += "\n\n-----------------------------     \n";
                    impressao += retornoSitef.textoImpressoEstabelecimento();
                }
                if (!impressao.isEmpty() && acao.equals("reimpressao")) {
                    dialogImpressao(impressao, 17);
                }
            }
            // Verifica se ocorreu um erro durante venda ou cancelamento
            if (acao.equals("venda") || acao.equals("cancelamento")) {
                if (retornoSitef.getCodResp().isEmpty() || !retornoSitef.getCodResp().equals("0") || retornoSitef.getCodResp() == null) {
                    //dialodTransacaoNegadaMsitef(retornoSitef);
                } else {
                    dialodTransacaoAprovadaMsitef(retornoSitef);
                }
                dialodTransacaoAprovadaMsitef(retornoSitef);
            }
        } else {
            // ocorreu um erro
            if (acao.equals("venda") || acao.equals("cancelamento")) {
                //dialodTransacaoNegadaMsitef(retornoSitef);
            }
        }
    }


    @ReactMethod
    public void efetuavenda (Boolean pinpad){
        Intent intentSitef = new Intent("br.com.softwareexpress.sitef.msitef.ACTIVITY_CLISITEF");
        intentSitef.putExtra("empresaSitef", "00000000");
        intentSitef.putExtra("enderecoSitef", "172.17.102.96"); // Ip do servidor que Tera O Sitef
        intentSitef.putExtra("operador", "0001");
        intentSitef.putExtra("data", "20200324");
        intentSitef.putExtra("hora", "130358");
        intentSitef.putExtra("numeroCupom", op);
        intentSitef.putExtra("valor", "10000");
        intentSitef.putExtra("CNPJ_CPF", "03654119000176");
        intentSitef.putExtra("comExterna", "0");
        if (pinpad){ // Verifica Se Fará a Transação via Blurtooth ou usb Pinpad
            intentSitef.putExtra("pinpadMac", "00:00:00:00:00:00");
        }
        if(false){
            intentSitef.putExtra("modalidade", "0");
        } else if (false) {
            intentSitef.putExtra("modalidade", "3");
            intentSitef.putExtra("numParcelas", "1");
        } else if (true) {
            intentSitef.putExtra("modalidade", "2");
            //intentSitef.putExtra("transacoesHabilitadas", "16");
        } else if (false) {
        }
        intentSitef.putExtra("isDoubleValidation", "0");
        intentSitef.putExtra("caminhoCertificadoCA", "ca_cert_perm");
        getCurrentActivity().startActivityForResult(intentSitef, REQ_CODE);
    }
    private void dialodTransacaoAprovadaMsitef(RetornoMsiTef retornoMsiTef) {
        Log.d("Teste", "dialog Transacao");
        StringBuilder cupom = new StringBuilder();
        StringBuilder teste = new StringBuilder();

        cupom.append("Via Cliente \n" + retornoMsiTef.getVIA_CLIENTE() + "\n");
        teste.append("Via Estabelecimento \n" + retornoMsiTef.getVIA_ESTABELECIMENTO() + "\n");



                if (getDeviceName().equals("SUNMI K2")){


                    kPrinterPresenter.setAlign(KTectoySunmiPrinter.Alignment_CENTER);
                    kPrinterPresenter.printStyleBold(true);
                    kPrinterPresenter.text(cupom.toString());
                    kPrinterPresenter.print3Line();
                    kPrinterPresenter.text(teste.toString());
                    kPrinterPresenter.print3Line();
                    kPrinterPresenter.cutpaper(2,2);


                }else {
                    Log.d("Teste", "Imprimindo");
                    TectoySunmiPrint.getInstance().setSize(20);
                    TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
                    TectoySunmiPrint.getInstance().printStyleBold(true);
                    TectoySunmiPrint.getInstance().printText(cupom.toString());
                    TectoySunmiPrint.getInstance().print3Line();
                    TectoySunmiPrint.getInstance().feedPaper();
                    TectoySunmiPrint.getInstance().printText(teste.toString());
                    TectoySunmiPrint.getInstance().print3Line();
                    TectoySunmiPrint.getInstance().cutpaper();
                }
            }



    public String respSitefToJson(Intent data) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("CODRESP", data.getStringExtra("CODRESP"));
        json.put("COMP_DADOS_CONF", data.getStringExtra("COMP_DADOS_CONF"));
        json.put("CODTRANS", data.getStringExtra("CODTRANS"));
        json.put("VLTROCO", data.getStringExtra("VLTROCO"));
        json.put("REDE_AUT", data.getStringExtra("REDE_AUT"));
        json.put("BANDEIRA", data.getStringExtra("BANDEIRA"));
        json.put("NSU_SITEF", data.getStringExtra("NSU_SITEF"));
        json.put("NSU_HOST", data.getStringExtra("NSU_HOST"));
        json.put("COD_AUTORIZACAO", data.getStringExtra("COD_AUTORIZACAO"));
        json.put("NUM_PARC", data.getStringExtra("NUM_PARC"));
        json.put("TIPO_PARC", data.getStringExtra("TIPO_PARC"));
        json.put("VIA_ESTABELECIMENTO", data.getStringExtra("VIA_ESTABELECIMENTO"));
        json.put("VIA_CLIENTE", data.getStringExtra("VIA_CLIENTE"));
        return json.toString();
    }
    private void dialogImpressao(String texto, int size) {
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.text(texto);
        }else {
            TectoySunmiPrint.getInstance().printText(texto);
        }
    }
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }



    @Override
    public void onNewIntent(Intent intent) {

    }
}
