import React, { Component, useState  } from "react";
import {Text, View, StyleSheet, FlatList, TouchableOpacity, Image, SafeAreaView, Alert, Button, Picker} from 'react-native';
import { Appbar } from 'react-native-paper';
import { NativeModules, NativeEventEmitter } from 'react-native';
import {LampadaSdkModule} from "../modules";
import { color } from "react-native-reanimated";


export default class Paygo extends React.Component{
  render() {
return(
<SafeAreaView style={styles.backgroudScreen}>

<View style={styles.button}>
  <Button  title="Pagar" onPress={()=>LampadaSdkModule.luzVermelha()} />
</View>
<View style={styles.button}>
  <Button  title="Administrativo" onPress={()=>LampadaSdkModule.luzVermelha()} />
</View>
</SafeAreaView>
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
