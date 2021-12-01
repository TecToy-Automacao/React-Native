import React from "react";
import { Text, View, StyleSheet, TouchableHighlight, Image, Button, Alert, TextInput } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

export default function Imagem() {
  return (

    <View style={styles.backgroudScreen}>

      <View style={styles.margemTop}>

        <TouchableHighlight onPress={() => { alert('Clicou no QrCode') }}>
          <View>
            <Text style={styles.tamanho}>Alinhamento
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>

        <View style={styles.linha} />

        <TouchableHighlight onPress={() => { alert('Clicou na quantidade de impressão') }}>
          <View style={styles.margi}>
            <Text style={styles.tamanho}>Modo impressão de imagem
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>

        <View style={styles.linha} />





  <View style={styles.margi}>
    <Text style={styles.tamanhoTeste}>Modo impressão de imagem</Text>
    <TextInput 
              autoCapitalize="none"
              keyboardType='default'
              autoCorrect={false}
              placeholder='Modelos '
              placeholderTextColor='gray'
              textAlign='center'
               />
        <TouchableHighlight onPress={() => { alert('Clicou na quanade de tidimpressão') }}>       
      <Icon name="chevron-right" style={styles.iconConfig} />
 
</TouchableHighlight>
</View>




        <View style={styles.linha} />

        <View style={styles.positionImage}>
          <Image
            source={require("../images/testImagem.jpg")} />
        </View>
      </View>

      <View style={styles.positionButton}>
        <Button color='red'
        title="Imprimir"
        onPress={() => Alert.alert('Clicou no botão para imprimir')}/>
        </View>

    </View>
  )
}

const styles = StyleSheet.create({
  backgroudScreen: {
    backgroundColor: '#000',
    flex: 1

  },

  corLinha: {
    color: 'white'
  },

  margemTop: {
    marginTop: 30,
    backgroundColor: '#000'
  },

  tamanho: {
    justifyContent: "center",
    alignItems: "center",
    fontSize: 25,
    color: 'white'
  },

  iconConfig: {
    fontSize: 20,
    color: 'white'
  },

  positionButton: {
    flex: 1,
    justifyContent: 'flex-end',
    marginBottom: 36,
    marginLeft: 20,
    marginRight: 20,
    height: 50,
    color: 'red'
  },

  positionImage: {
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 80
  },

  linha: {
    borderBottomColor: 'white',
    borderBottomWidth: 1,
    marginLeft: 5,
    marginRight: 5
  },

  margi:{
    marginTop:10
  },

  tamanhoTeste: {
    justifyContent: "center",
    alignItems: "center",
    fontSize: 20,
    color: 'white'
  },

});