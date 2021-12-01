import React, { Component, useState  } from "react";
import {Text, View, StyleSheet, FlatList, TouchableOpacity, Image, SafeAreaView, Alert, Button, Picker} from 'react-native';
import { Appbar } from 'react-native-paper';

export default class QrCode extends React.Component{
  render() {
return(
<SafeAreaView>
<View>
  <Button title="Imprimir" onPress={()=> Alert.alert("imprimir")} />
</View>
</SafeAreaView>
);
}
};
