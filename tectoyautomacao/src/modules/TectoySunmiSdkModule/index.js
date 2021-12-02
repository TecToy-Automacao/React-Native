import {NativeModules} from 'react-native';

const {TectoySunmiSdkModule} = NativeModules;


export const printCupomCompleto = async () =>{
    try{
        aling(1);
        printText("Alinhamento\n");
        printText("--------------------------------\n");
        aling(0);
        printText("TecToy Automação\n");
        aling(1);
        printText("TecToy Automação\n");
        aling(2);
        printText("TecToy Automação\n");
        aling(1);
        printText("Formas de Impressão\n");
        printText("--------------------------------\n");
        aling(0);
        bold(true);
        printText("TecToy Automação\n");
        resetStyle();
        printStyleAntiWhite();
        printText("TecToy Automação\n");
        resetStyle();
        doubleheight(true);
        printText("TecToy Automação\n");
        resetStyle();
        doublewidth(true);
        printText("TecToy Automação\n");
        resetStyle();
        styleInvert(true);
        printText("TecToy Automação\n");
        resetStyle();
        styleItalic(true);
        printText("TecToy Automação\n");
        resetStyle();
        styleStrike(true);
        printText("TecToy Automação\n");
        resetStyle();
        styleUnderline(true);
        printText("TecToy Automação\n");
        resetStyle();
        aling(0);
        printTextWithSize("TecToy Sunmi\n", 35);
        aling(1);
        printTextWithSize("TecToy Sunmi\n", 28);
        aling(2);
        printTextWithSize("TecToy Sunmi\n", 50);
        print3lines();
        aling(1);
        printText("Imprime Barcode\n");
        printText("--------------------------------\n");
        aling(0);
        printBarcode("7894900700046", 2, 162, 2, 0);
        aling(1);
        printAdvancesLines(1);
        printBarcode("7894900700046", 2, 162, 2, 2);
        aling(2);
        printAdvancesLines(1);
        printBarcode("7894900700046", 2, 162, 2, 1);
        aling(1);
        printAdvancesLines(1);
        printBarcode("7894900700046", 2, 162, 2, 3);
        aling(1);
        printAdvancesLines(1);
        printText("Imprime QrCode\n");
        printText("--------------------------------\n");
        aling(0);
        printQr("www.tectoysunmi.com.br", 8, 1);
        aling(1);
        printAdvancesLines(1);
        printQr("www.tectoysunmi.com.br", 8, 1);
        aling(2);
        printAdvancesLines(1);
        printQr("www.tectoysunmi.com.br", 8, 1);
        aling(0);
        printDoubleQr("www.tectoysunmi.com.br","tectoy", 8, 1);
        aling(1);
        printText("Imprime Imagem\n");
        printText("--------------------------------\n");
        printBitmap();
        aling(0);
        printBitmap();
        aling(2);
        printBitmap();
        print3lines();
    }catch(error){
    reject(error);
    }
};


export const printBitmap = async () =>{
    try {
        await TectoySunmiSdkModule.printBitmap();
    }catch(error){
        reject(error);
    }
};
export const printQr = async (txt, modo, error) =>{
    try{
        await TectoySunmiSdkModule.printQr(txt, modo, error);
    }catch(error){
        reject(error);
    }
};
export const printDoubleQr = async (txt, txt1, modo, error) =>{
    try{
        await TectoySunmiSdkModule.printDoubleQr(txt, txt1, modo, error);
    }catch(error){
        reject(error);
    }
};
export const printAdvancesLines = async (av) =>{
    try{
        await TectoySunmiSdkModule.printAdvancesLines(av);
    }catch(error){
        reject(error);
    }
};
export const styleUnderline = async (bool) =>{
    try{
        await TectoySunmiSdkModule.styleUnderline(bool);
    }catch(error){
        reject(error);
    }
};
export const styleStrike = async (bool) =>{
    try{
        await TectoySunmiSdkModule.styleStrike(bool);
    }catch(error){
        reject(error);
    }
};
export const printTextWithSize = async (txt, size) =>{
    try{
        await TectoySunmiSdkModule.printTextWithSize(txt, size);
    }catch(error){
        reject(error);
    }
};
export const styleInvert = async (bool) =>{
    try{
        await TectoySunmiSdkModule.styleInvert(bool);
    }catch(error){
        reject(error);
    }
};
export const styleItalic = async (bool) =>{
    try{
        await TectoySunmiSdkModule.styleItalic(bool);
    }catch(error){
        reject(error);
    }
};
export const doublewidth = async (bool) =>{
    try{
        await TectoySunmiSdkModule.styleDoubleWidth(bool);
    }catch(erro){
        reject(error);
    }
};
export const doubleheight = async (bool) =>{
    try{
        await TectoySunmiSdkModule.styleDoubleHeight(bool);
    }catch(error){
        reject(error);
    }
};

export const resetStyle = async () =>{
    try{
        await TectoySunmiSdkModule.resetStyle();
    }catch(error){
        reject(error);
    }
};
export const printStyleAntiWhite = async () =>{
    try{
        await TectoySunmiSdkModule.printStyleAntiWhite();
    }catch(error){
        reject(error);
    }
};
export const aling = async (aling) =>{
    try{
        await TectoySunmiSdkModule.aling(aling);
    }catch(error){
        reject(error);
    }
};

export const printText = async (texto) =>{
    try{
        console.log("Estou aqui");
        await TectoySunmiSdkModule.printText(texto);
    }catch(error){
        reject(error);
    }
};

export const print3lines = async () =>{
    try{
        await TectoySunmiSdkModule.print3lines();
    }catch(error){
        reject(error);
    }
};

export const getstatus = async () =>{
    try{
        await TectoySunmiSdkModule.getstatus();
    }catch(error){
        reject(error);
    }
};

export const  printBarcode = async (texto, tipo, wigth, height, hripos) =>{
    try{
        console.log("Barcode");
        await TectoySunmiSdkModule.barcode(texto, tipo, wigth, height, hripos);
    }catch(error){
        reject(error);
    }
};

export const bold = async (bold) =>{
    try{
        await TectoySunmiSdkModule.printSytlebold(bold);
    }catch(error){
        reject(error);
    }
};