import React, { Component, useState  } from "react";
import {Text, View, StyleSheet, FlatList, TouchableOpacity, Image, SafeAreaView, Alert, Button, Picker} from 'react-native';
import { Appbar } from 'react-native-paper';

export default class QrCode extends React.Component{
  render() {
return(
<SafeAreaView style={styles.backgroudScreen}>
<View>
  <Button style={styles.button} title="Imprimir" onPress={()=> Alert.alert("imprimir")} />
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
    color: '#ff0000'
  },
  backgroudScreen: {
    backgroundColor: '#000',
  },
});
