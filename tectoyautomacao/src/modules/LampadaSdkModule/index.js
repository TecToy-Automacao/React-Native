import {NativeModules} from 'react-native';

const {LampadaSdkModule} = NativeModules;

export const luzverde = async () =>{
    try {
        await LampadaSdkModule.luzVerde();
    }catch(error){
        reject(error);
    }
};

export const luzVermelha = async () =>{
    try {
        await LampadaSdkModule.luzVermelha();
    }catch(error){
        reject(error);
    }
};
export const luzvluzAzulClaro = async () =>{
    try {
        await LampadaSdkModule.luzAzulClaro();
    }catch(error){
        reject(error);
    }
};

export const luzRoxa = async () =>{
    try {
        await LampadaSdkModule.luzRoxa();
    }catch(error){
        reject(error);
    }
};

export const luzAmarela = async () =>{
    try {
        await LampadaSdkModule.luzAmarela();
    }catch(error){
        reject(error);
    }
};
luzAzul
export const luzAzul = async () =>{
    try {
        await LampadaSdkModule.luzAzul();
    }catch(error){
        reject(error);
    }
};

export const desligar = async () =>{
    try {
        await LampadaSdkModule.desligar();
    }catch(error){
        reject(error);
    }
};