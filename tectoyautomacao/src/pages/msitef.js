import React, { Component, useState, useEffect  } from "react";
import {Text, View, StyleSheet, FlatList, TouchableOpacity, Image, DeviceEventEmitter, SafeAreaView, Alert, Button, Picker} from 'react-native';
import { Appbar } from 'react-native-paper';
import { NativeModules, NativeEventEmitter } from 'react-native';
import {MsitefSdkModule, TectoySunmiSdkModule} from "../modules";
import { color } from "react-native-reanimated";


const Msitef = () => {

// export default class Msitef{



  // render() {
    const [ listaRetorno, setListaRetorno ] = useState([]);

    useEffect(() => {
          
      DeviceEventEmitter.addListener("via_estabelecimento", event => {
        var retorno = event.result;
        console.log(retorno.toString());
        TectoySunmiSdkModule.aling(1);
        TectoySunmiSdkModule.printText(retorno.toString());
        TectoySunmiSdkModule.print3lines();
    });
    DeviceEventEmitter.addListener("via_cliente", event => {
      var retorno = event.result;
      console.log(retorno.toString());
      TectoySunmiSdkModule.aling(1);
      TectoySunmiSdkModule.printText(retorno.toString());
      TectoySunmiSdkModule.print3lines(); 
  });
  DeviceEventEmitter.addListener("dados", event => {
    var retorno = event.result;
    console.log(retorno.toString());
    TectoySunmiSdkModule.aling(1);
    TectoySunmiSdkModule.printText(retorno.toString());
    TectoySunmiSdkModule.print3lines(); 
});
    

  
  },[listaRetorno]);
  
  return(
    <SafeAreaView style={styles.backgroudScreen}>
      <View style={styles.button}>
        <Button  title="Pagamento Bluetooth" onPress={()=>MsitefSdkModule.efetuavenda(false, "00000000", "172.17.102.96", "0001", "10000", "03654119000176") } />
      </View>
      <View style={styles.button}>
        <Button  title="Pagamento Usb" onPress={()=>MsitefSdkModule.efetuavenda(true, "00000000", "172.17.102.96", "0001", "10000", "03654119000176") } />
      </View>
    </SafeAreaView>
  );
}
// };

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    alignItems: 'center',
    justifyContent: 'space-between',
    alignSelf: 'auto',
    paddingLeft: 20,
    margin: 20
  },
  button: {
    backgroundColor: '#181a26',
    paddingLeft: 20,
    paddingRight: 20,
    paddingVertical: 20   
  },
  backgroudScreen: {
    backgroundColor: '#181a26',
  },
});

export default Msitef;