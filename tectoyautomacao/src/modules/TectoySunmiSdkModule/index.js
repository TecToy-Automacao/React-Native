import {NativeModules} from 'react-native';

const {TectoySunmiSdkModule} = NativeModules;

export const printCupomCompleto = async () =>{
    try{
        await TectoySunmiSdkModule.printCupomCompleto();
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