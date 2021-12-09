package br.com.tectoyautomacao.tectoysunmisdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.sunmi.extprinterservice.ExtPrinterService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Locale;

public class TectoySunmiSdkModule extends ReactContextBaseJavaModule {

    private ExtPrinterService extPrinterService = null;
    public static KTectoySunmiPrinter kPrinterPresenter;

    TectoySunmiSdkModule(ReactApplicationContext context) {
        super(context);
        if (getDeviceName().equals("SUNMI K2")){
            System.out.println("K2");
            connectKPrintService();
        }else {
            Log.d("Teste", "Conect");
            TectoySunmiPrint.getInstance().initSunmiPrinterService(context);
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"/MeuApp/imagens/");
            directory.mkdirs();
        }
    }


    @NonNull
    @Override
    public String getName() {
        return "TectoySunmiSdkModule";
    }

    private Bitmap getBitmapFromURL(String url) {
        try {
            URL src = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) src.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void salvando(Bitmap abmp){

        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/MeuApp/imagens/";
        File dir = new File(file_path);
        Log.d("Geovai", String.valueOf(abmp));
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, "nomedaImagembaixada");
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            ;
            abmp.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @ReactMethod
    public void printGGG(String uri) throws IOException {


        String encodstring = "data:image/jpeg;base64,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";


        System.out.println("Base64:" + encodstring);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = 160;
        options.inDensity = 160;

        Bitmap image = BitmapFactory.decodeFile(encodstring.replace(
                "file:///", ""), options);
        System.out.println("image:" + image);
       // image = scaleImage(image);

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inTargetDensity = 160;
        o.inDensity = 160;
        Bitmap bitmap = BitmapFactory.decodeResource(getReactApplicationContext().getResources(), R.drawable.test1, o);
        Bitmap bitmap1 = scaleImage(bitmap);
        if (getDeviceName().equals("SUNMI K2")) {
            kPrinterPresenter.printBitmap(bitmap1, 0);
        } else {
            TectoySunmiPrint.getInstance().printBitmap(bitmap1);
        }
    }
    @ReactMethod
    public void printStyleAntiWhite(){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleAntiWhite(true);
        }
    }
    @ReactMethod
    public void resetStyle(){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleReset();
        }
    }
    @ReactMethod
    public void styleUnderline(Boolean underline){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.printStyleUnderLine();
        }else {
            TectoySunmiPrint.getInstance().printStyleUnderLine(underline);
        }
    }
    @ReactMethod
    public void aling(int aling){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.setAlign(aling);
        }else {
            TectoySunmiPrint.getInstance().setAlign(aling);
        }
    }
    @ReactMethod
    public void printTable(String[] txt, int[] width, int[] aling){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.printTable(txt, width, aling);
        }else {
            TectoySunmiPrint.getInstance().printTable(txt, width, aling);
        }
    }
    @ReactMethod
    public void styleDoubleHeight(Boolean bold){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleDoubleHeight(bold);
        }
    }
    @ReactMethod
    public void styleDoubleWidth(Boolean bold){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleDoubleWidth(bold);
        }
    }
    @ReactMethod
    public void  printSytlebold(Boolean bold){
        if (getDeviceName().equals("SUNMI K2")) {
            kPrinterPresenter.printStyleBold(bold);
        }else {
            TectoySunmiPrint.getInstance().printStyleBold(bold);
        }
    }
    @ReactMethod
    public void cutpaper(){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.cutpaper(1,2);
        }else {
            TectoySunmiPrint.getInstance().cutpaper();
        }
    }
    @ReactMethod
    public void styleInvert(Boolean invert){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleInvert(invert);
        }
    }

    @ReactMethod
    public void styleStrike(Boolean bool){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleStrikethRough(bool);
        }
    }
    @ReactMethod
    public void printTextWithSize(String texto, int size){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printTextWithSize(texto,size);
        }
    }
    @ReactMethod
    public void styleItalic(Boolean italic){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printStyleItalic(italic);
        }
    }
    @ReactMethod
    public void printQr(String txt, int modulo, int error){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.printQr(txt, modulo, error);
        }else {
            TectoySunmiPrint.getInstance().printQr(txt, modulo, error);
        }
    }
    @ReactMethod
    public void printDoubleQr(String txt, String txt1, int modo, int error){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printDoubleQRCode(txt,txt1, modo, error);
        }
    }
    @ReactMethod
    public void printAdvancesLines(int av){
        if (getDeviceName().equals("SUNMI K2")){

        }else {
            TectoySunmiPrint.getInstance().printAdvanceLines(av);
        }
    }
    @ReactMethod
    public void printText(String texto){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.text(texto);
        }else {
            TectoySunmiPrint.getInstance().printText(texto);
        }
    }
    @ReactMethod
    public void printCupomCompleto() {
        if (getDeviceName().equals("SUNMI K2")){
            KtexteCompelto();
        }else {
            tecteCompleto();
        }
    }

    @ReactMethod
    public void print3lines(){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.print3Line();
        }else {
            TectoySunmiPrint.getInstance().print3Line();
        }
    }

    @ReactMethod
    public void getStatus(){
        if (getDeviceName().equals("SUNMI K2")){
            String result = ""+ kPrinterPresenter.getStatus();
            WritableMap params = Arguments.createMap();
            params.putString("result",result);
            getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("getstatus", params);
          }else {
            String result = TectoySunmiPrint.getInstance().printerStatus();
            WritableMap params = Arguments.createMap();
            params.putString("result",result);
            getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("getstatus", params);
        }
    }

    @ReactMethod
    public void barcode(String texto, int tipo, int weigth, int height, int hripos){
        if(getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.barcode(texto, tipo, weigth, height, hripos);
        }else {
            System.out.println("Aqui");
            TectoySunmiPrint.getInstance().initPrinter();
            TectoySunmiPrint.getInstance().printBarCode(texto, tipo, weigth, height, hripos);
            TectoySunmiPrint.getInstance().feedPaper();
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
    private void connectKPrintService() {
        Intent intent = new Intent();
        intent.setPackage("com.sunmi.extprinterservice");
        intent.setAction("com.sunmi.extprinterservice.PrinterService");
        getReactApplicationContext().bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            extPrinterService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            extPrinterService = ExtPrinterService.Stub.asInterface(service);
            kPrinterPresenter = new KTectoySunmiPrinter(getReactApplicationContext(), extPrinterService);
        }
    };

    private void tecteCompleto(){
        TectoySunmiPrint.getInstance().initPrinter();
        TectoySunmiPrint.getInstance().setSize(24);

        // Alinhamento do texto
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printText("Alinhamento\n");
        TectoySunmiPrint.getInstance().printText("--------------------------------\n");
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_LEFT);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_RIGTH);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");

        // Formas de impressão
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printText("Formas de Impressão\n");
        TectoySunmiPrint.getInstance().printText("--------------------------------\n");
        TectoySunmiPrint.getInstance().setSize(28);
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_LEFT);
        TectoySunmiPrint.getInstance().printStyleBold(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleAntiWhite(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleDoubleHeight(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleDoubleWidth(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleInvert(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleItalic(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleStrikethRough(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().printStyleUnderLine(true);
        TectoySunmiPrint.getInstance().printText("TecToy Sunmi\n");
        TectoySunmiPrint.getInstance().printStyleReset();
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_LEFT);
        TectoySunmiPrint.getInstance().printTextWithSize("TecToy Sunmi\n", 35);
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printTextWithSize("TecToy Sunmi\n", 28);
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_RIGTH);
        TectoySunmiPrint.getInstance().printTextWithSize("TecToy Sunmi\n",50);
        TectoySunmiPrint.getInstance().feedPaper();

        TectoySunmiPrint.getInstance().setSize(24);
        // Impressão de BarCode
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printText("Imprime BarCode\n");
        TectoySunmiPrint.getInstance().printText("--------------------------------\n");
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_LEFT);
        TectoySunmiPrint.getInstance().printBarCode("7894900700046", TectoySunmiPrint.BarCodeModels_EAN13, 162, 2,
                TectoySunmiPrint.BarCodeTextPosition_INFORME_UM_TEXTO);
        TectoySunmiPrint.getInstance().printAdvanceLines(2);
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printBarCode("7894900700046", TectoySunmiPrint.BarCodeModels_EAN13, 162, 2,
                TectoySunmiPrint.BarCodeTextPosition_ABAIXO_DO_CODIGO_DE_BARRAS);
        TectoySunmiPrint.getInstance().printAdvanceLines(2);
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_RIGTH);
        TectoySunmiPrint.getInstance().printBarCode("7894900700046", TectoySunmiPrint.BarCodeModels_EAN13, 162, 2,
                TectoySunmiPrint.BarCodeTextPosition_ACIMA_DO_CODIGO_DE_BARRAS_BARCODE);
        TectoySunmiPrint.getInstance().printAdvanceLines(2);
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printBarCode("7894900700046", TectoySunmiPrint.BarCodeModels_EAN13, 162, 2,
                TectoySunmiPrint.BarCodeTextPosition_ACIMA_E_ABAIXO_DO_CODIGO_DE_BARRAS);
        TectoySunmiPrint.getInstance().feedPaper();

        // Impressão de BarCode
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printText("Imprime QrCode\n");
        TectoySunmiPrint.getInstance().printText("--------------------------------\n");
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_LEFT);
        TectoySunmiPrint.getInstance().printQr("www.tectoysunmi.com.br", 8, 1);
        TectoySunmiPrint.getInstance().feedPaper();
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printQr("www.tectoysunmi.com.br", 8, 1);
        TectoySunmiPrint.getInstance().feedPaper();
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_RIGTH);
        TectoySunmiPrint.getInstance().printQr("www.tectoysunmi.com.br", 8, 1);
        TectoySunmiPrint.getInstance().feedPaper();
        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_LEFT);
        TectoySunmiPrint.getInstance().printDoubleQRCode("www.tectoysunmi.com.br","tectoysunmi", 7, 1);
        TectoySunmiPrint.getInstance().feedPaper();

        TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
        TectoySunmiPrint.getInstance().printText("Imprime Tabela\n");
        TectoySunmiPrint.getInstance().printText("--------------------------------\n");

        String[] prod = new String[3];
        int[] width = new int[3];
        int[] align = new int[3];

        width[0] = 100;
        width[1] = 50;
        width[2] = 50;

        align[0] = TectoySunmiPrint.Alignment_LEFT;
        align[1] = TectoySunmiPrint.Alignment_CENTER;
        align[2] = TectoySunmiPrint.Alignment_RIGTH;

        prod[0] = "Produto 001";
        prod[1] = "10 und";
        prod[2] = "3,98";
        TectoySunmiPrint.getInstance().printTable(prod, width, align);

        prod[0] = "Produto 002";
        prod[1] = "10 und";
        prod[2] = "3,98";
        TectoySunmiPrint.getInstance().printTable(prod, width, align);

        prod[0] = "Produto 003";
        prod[1] = "10 und";
        prod[2] = "3,98";
        TectoySunmiPrint.getInstance().printTable(prod, width, align);

        prod[0] = "Produto 004";
        prod[1] = "10 und";
        prod[2] = "3,98";
        TectoySunmiPrint.getInstance().printTable(prod, width, align);

        prod[0] = "Produto 005";
        prod[1] = "10 und";
        prod[2] = "3,98";
        TectoySunmiPrint.getInstance().printTable(prod, width, align);

        prod[0] = "Produto 006";
        prod[1] = "10 und";
        prod[2] = "3,98";
        TectoySunmiPrint.getInstance().printTable(prod, width, align);

        TectoySunmiPrint.getInstance().print3Line();
        TectoySunmiPrint.getInstance().openCashBox();
        TectoySunmiPrint.getInstance().cutpaper();
    }
    private void KtexteCompelto(){
        // Alinhamento
        kPrinterPresenter.printStyleBold(false);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("Alinhamento\n");
        kPrinterPresenter.text("--------------------------------\n");
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_LEFT);
        kPrinterPresenter.text("TecToy Automação\n");
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("TecToy Automação\n");
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_RIGTH);
        kPrinterPresenter.text("TecToy Automação\n");
        kPrinterPresenter.print3Line();

        // Formas de impressão

        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("Formas de Impressão\n");
        kPrinterPresenter.text("--------------------------------\n");
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_LEFT);
        kPrinterPresenter.printStyleBold(true);
        kPrinterPresenter.text("TecToy Automação\n");


        // Barcode

        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("BarCode\n");
        kPrinterPresenter.text("--------------------------------\n");
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_LEFT);
        kPrinterPresenter.printBarcode("7891098010575", 2, 162, 2, 0);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.printBarcode("7891098010575", 2, 162, 2, 2);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_RIGTH);
        kPrinterPresenter.printBarcode("7891098010575", 2, 162, 2, 1);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.printBarcode("7891098010575", 2, 162, 2, 3);
        kPrinterPresenter.print3Line();
        // QrCode

        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("QrCode\n");
        kPrinterPresenter.text("--------------------------------\n");
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.printQr("www.tectoyautomacao.com.br", 8, 0);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_LEFT);
        kPrinterPresenter.printQr("www.tectoyautomacao.com.br", 8, 0);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_RIGTH);
        kPrinterPresenter.printQr("www.tectoyautomacao.com.br", 8, 0);
        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_LEFT);
        kPrinterPresenter.printDoubleQRCode("www.tectoyautomacao.com.br","tectoy", 7, 1);
        // Imagem


        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("Imagem\n");
        kPrinterPresenter.text("--------------------------------\n");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = 160;
        options.inDensity = 160;
        Bitmap bitmap1 = null;
        if (bitmap1 == null) {
            bitmap1 = BitmapFactory.decodeResource(getReactApplicationContext().getResources(), R.drawable.test1, options);
            bitmap1 = scaleImage(bitmap1);
        }
        kPrinterPresenter.printBitmap(bitmap1,  0);
        kPrinterPresenter.setAlign(0);
        kPrinterPresenter.printBitmap(bitmap1,  0);
        kPrinterPresenter.setAlign(2);
        kPrinterPresenter.printBitmap(bitmap1,  0);
        // Tabelas


        kPrinterPresenter.setAlign(kPrinterPresenter.Alignment_CENTER);
        kPrinterPresenter.text("Tabelas\n");
        kPrinterPresenter.text("--------------------------------\n");
        String[] prod = new String[3];
        int[] width = new int[3];
        int[] align = new int[3];

        width[0] = 100;
        width[1] = 50;
        width[2] = 50;

        align[0] = kPrinterPresenter.Alignment_LEFT;
        align[1] = kPrinterPresenter.Alignment_CENTER;
        align[2] = kPrinterPresenter.Alignment_RIGTH;

        prod[0] = "Produto 001";
        prod[1] = "10 und";
        prod[2] = "3,98";
        kPrinterPresenter.printTable(prod, width, align);

        prod[0] = "Produto 002";
        prod[1] = "10 und";
        prod[2] = "3,98";
        kPrinterPresenter.printTable(prod, width, align);


        prod[0] = "Produto 003";
        prod[1] = "10 und";
        prod[2] = "3,98";
        kPrinterPresenter.printTable(prod, width, align);


        prod[0] = "Produto 004";
        prod[1] = "10 und";
        prod[2] = "3,98";
        kPrinterPresenter.printTable(prod, width, align);


        prod[0] = "Produto 005";
        prod[1] = "10 und";
        prod[2] = "3,98";
        kPrinterPresenter.printTable(prod, width, align);

        prod[0] = "Produto 006";
        prod[1] = "10 und";
        prod[2] = "3,98";
        kPrinterPresenter.printTable(prod, width, align);


        kPrinterPresenter.print3Line();
        kPrinterPresenter.cutpaper(KTectoySunmiPrinter.HALF_CUTTING, 10);
    }
    private Bitmap scaleImage(Bitmap bitmap1) {
        int width = bitmap1.getWidth();
        int height = bitmap1.getHeight();
        int newWidth = (width / 8 + 1) * 8;
        float scaleWidth = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, 1);
        return Bitmap.createBitmap(bitmap1, 0, 0, width, height, matrix, true);
    }
}
