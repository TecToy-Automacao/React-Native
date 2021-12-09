/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */


import React, { useState, useEffect  } from "react";
import {readFile} from 'react-native-fs';
import exemplo from './images/testImagem.jpg';
import fetch_blob from 'react-native-fetch-blob';
import {
  SafeAreaView,
  FlatList,
  Image,
  DeviceEventEmitter,
  TouchableOpacity,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';

//import { Appbar } from 'react-native-paper';

import { NativeModules, NativeEventEmitter } from 'react-native';
import {TectoySunmiSdkModule} from "./modules";

//var TectoySunmiSdk = NativeModules.TectoySunmiSdkModule;

class App extends React.Component {


  state = {
    data: [
      { id: "00", name: "Teste Completo", image:require('./images/function_all.png'), onPress: ()=>TectoySunmiSdkModule.printCupomCompleto() },
    //  { id: "01", name: "Qr Code", image:require('./images/function_qr.png'), onPress: ()=>this.props.navigation.navigate("Qr_CODE") },
    //  { id: "02", name: "Bar Code", image:require('./images/function_barcode.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    //  { id: "03", name: "Text", image:require('./images/function_text.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    //  { id: "04", name: "Formulário", image:require('./images/function_tab.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    //  { id: "05", name: "Imagem", image:require('./images/function_pic.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    //  { id: "06", name: "Leitura BarCode", image:require('./images/function_scanner.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    //  { id: "07", name: "Avança Papel", image:require('./images/function_threeline.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    //  { id: "08", name: "Gaveta", image:require('./images/function_cash.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
     { id: "09", name: "Msitef", image:require('./images/function_payment.png'), onPress: ()=>this.props.navigation.navigate("Msitef") },
     { id: "10", name: "Lamp", image:require('./images/function_led.png'), onPress: ()=>this.props.navigation.navigate("Lampada_K2")  },
    //  { id: "11", name: "Etiqueta", image:require('./images/function_label.png'), onPress: ()=>TectoySunmiSdk.printCupomCompleto() },
    ]
  };
  render() {

    const columns = 2;
  
    return (
      <View style={styles.backgroudScreen} >
        <SafeAreaView>
            <ScrollView horizontal>
              <FlatList
                data={this.state.data}
                keyExtractor={item => item.id}
                numColumns={columns}
                renderItem={({ item }) => {
                  console.log(Image.resolveAssetSource(exemplo).uri);
                  
                  return (
                    <View style={styles.item}>
                      <TouchableOpacity style={{flexDirection:'column',alignItems:'center',justifyContent:'space-between'}} onPress={item.onPress}>
                        <Image style={styles.imagem_logo} source={item.image} />
                        <Text style={styles.text}>{item.name}</Text>
                      </TouchableOpacity>
                    </View>
                  );
                }}
              />
            </ScrollView>
          </SafeAreaView>
      </View>
    );
  }
};

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
  item: {
    flexBasis: 0,
    alignItems: "center",
    flexGrow: 1,
    padding: 38,
    borderWidth: 1,
    borderColor: "#DCDCDC",
  },
  text: {
    color: "#FF0000",
    alignItems: 'center'
  },
  imagem_logo: {
    width: 100,
    height: 100,
  },
  backgroudScreen: {
    backgroundColor: '#000',
  },
  appbar: {
    backgroundColor: '#222222',
    elevation: 5
  },

});


export default App;
