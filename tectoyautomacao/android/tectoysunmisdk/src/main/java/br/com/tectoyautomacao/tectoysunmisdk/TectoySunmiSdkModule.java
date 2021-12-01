package br.com.tectoyautomacao.tectoysunmisdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.sunmi.extprinterservice.ExtPrinterService;

public class TectoySunmiSdkModule extends ReactContextBaseJavaModule {

    private ExtPrinterService extPrinterService = null;
    public static KTectoySunmiPrinter kPrinterPresenter;

    TectoySunmiSdkModule(ReactApplicationContext context) {
        super(context);
        if (getDeviceName().equals("SUNMI K2")){
            System.out.println("K2");
            connectKPrintService();
        }else {
            TectoySunmiPrint.getInstance().initSunmiPrinterService(context);
        }
    }


    @NonNull
    @Override
    public String getName() {
        return "TectoySunmiSdkModule";
    }

    @ReactMethod
    public void printText(String texto){
        if (getDeviceName().equals("SUNMI K2")){
            kPrinterPresenter.text(texto);
            kPrinterPresenter.print3Line();
        }else {
            System.out.println("printtext");
            TectoySunmiPrint.getInstance().initPrinter();
            TectoySunmiPrint.getInstance().setSize(24);
            TectoySunmiPrint.getInstance().setAlign(TectoySunmiPrint.Alignment_CENTER);
            TectoySunmiPrint.getInstance().printText(texto);
            TectoySunmiPrint.getInstance().feedPaper();
        }
    }
    @ReactMethod
    public void printCupomCompleto() {
        if (getDeviceName().equals("SUNMI K2")){
            KtexteCompelto();
        }else {
            tecteCompleto();
        }
    }  public static String getDeviceName() {
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
