import {NativeModules} from 'react-native';

const {MsitefSdkModule} = NativeModules;

export const efetuavenda = async (bool) =>{
    try {
        await MsitefSdkModule.efetuavenda(bool);
    }catch(error){
        reject(error);
    }
};