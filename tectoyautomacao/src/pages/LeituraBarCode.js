import React from "react";
import { Text, View, Button, Alert, StyleSheet } from 'react-native';

export default function BarCode() {
  return (

    <View>
      <View style={styles.botao}>
        <Button
          title="Leitura BarCode/QrCode"
          onPress={() => Alert.alert('Apertou o botão leitura barCode/qrCode')}
        />
      </View>

      <View style={styles.botao2}>
        <Button 
          title="P2"
          onPress={() => Alert.alert('Apertou o botão P2')}
        />
      </View>

      <Text style={styles.texto}>Scanner Reultado: Unknown</Text>

    </View>

  )
}

const styles = StyleSheet.create({
  texto: {
    textAlign: 'center',
    marginTop: 1,
    color: 'black'
  },

  botao: {
    marginTop: 90,
    justifyContent: 'center',
    alignItems: 'center'

  },

  botao2: {
    marginTop: 15,
    justifyContent: 'center',
    alignItems: 'center'
  }
})