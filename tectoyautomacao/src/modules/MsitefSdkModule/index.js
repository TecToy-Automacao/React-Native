import {NativeModules} from 'react-native';

const {MsitefSdkModule} = NativeModules;

export const efetuavenda = async ( pinpad,  empresaSitef,  enderecoSitef,  operador,  valor,  CNPJ_CPF) =>{
    try {
        await MsitefSdkModule.efetuavenda(pinpad,  empresaSitef,  enderecoSitef,  operador,  valor,  CNPJ_CPF);
    }catch(error){
        reject(error);
    }
};