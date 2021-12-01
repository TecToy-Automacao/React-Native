
import React, { useState } from "react";
import { Text, View, TouchableHighlight, StyleSheet, FlatList, Button, Alert } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import CheckBox from '@react-native-community/checkbox';

export default function Texto() {

  const [isSelected, setSelection] = useState(false);

  return (

    <View style={styles.conteiner}>
      <View>
        <TouchableHighlight onPress={() => { alert('Clicou no nível de correção') }}>
          <View>
            <Text style={styles.tamanho}>Tamanho da impressão
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>

        <View
          style={styles.linha} />

        <View style={styles.checkboxView}>
          <CheckBox style={styles.checkbox}
            disabled={false}
            value={isSelected}
            onValueChange={(newValue) => setSelection(newValue)}
          />
          <Text style={styles.corTexto}>BOLD</Text>
          <CheckBox style={styles.checkbox}
            disabled={false}
            value={isSelected}
            onValueChange={(newValue) => setSelection(newValue)}
          />
          <Text style={styles.corTexto}>UNDERLINE</Text>
        </View>

        <View style={styles.lista}>
        <FlatList 
        data={[
          {key: 'Devin'},
          {key: 'Dan'},
          {key: 'Dominic'},
          {key: 'Jackson'},
          {key: 'James'},
          {key: 'Joel'},
          {key: 'John'},
          {key: 'Jillian'},
          {key: 'Jimmy'},
          {key: 'Julie'},
         
        ]}
        renderItem={({item}) => <Text style={styles.textoLista}>{item.key}</Text>}
      />
        </View>

        <View style={styles.botao}>
        <Button color= 'red'
        title="Imprimir"
        onPress={() => Alert.alert('Simple Button pressed')}/>
        </View>

      </View>
    </View>

  )
}

const styles = StyleSheet.create({
  conteiner: {
    flex: 1,
    backgroundColor: 'black'
  },

  iconConfig: {
    fontSize: 20,
    color: 'white'
  },

  corTexto: {
    color: 'white',
    justifyContent: 'center'
  },

  tamanho: {
    marginTop:30,
    justifyContent: "center",
    alignItems: "center",
    fontSize: 25,
    color: 'white'
  },

  checkboxContainer: {
    flexDirection: "row",
    marginBottom: 20
  },
  checkbox: {
    justifyContent: 'center',
    marginLeft: 20

  },

  checkboxView: {
    flexDirection: 'row',
    height: 50,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'gray',
    marginTop: 15,
    marginLeft: 20,
    marginRight: 20

  },

  linha:{
    borderBottomColor: 'white',
    borderBottomWidth: 1,
    marginLeft: 5,
    marginRight: 5
  },

  lista:{
    backgroundColor: 'white',
    marginLeft: 20,
    marginRight: 20,
    marginTop:10
  },

  textoLista:{
    color: 'black'
  },

  botao:{
    marginRight: 20,
    marginLeft: 20,
    height: 50
  }
})