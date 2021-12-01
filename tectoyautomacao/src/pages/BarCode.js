import React, { useState } from 'react';
import { View, Text, StyleSheet, Button, Image, TouchableHighlight, Alert, SafeAreaView, 
  ScrollView, FlatList, Modal, TextInput } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import { mostraModal } from '../src/components/Modal';




export default function BarCode() {

 
  const [modalVisible, setModalVisible] = useState(false);
  const [codigoTests, setCodigoTeste] = useState('');


  const [value, setValue] = useState('')
  const [editable, setEditable] = useState(true)
  const accessibilityState = { disabled: !editable }

 

  return (

    <ScrollView>

      <SafeAreaView style={styles.conteiner}>

      <Modal
            animationType="fade"
            transparent={true}
            visible={modalVisible} >

            <View style={styles.centeredView}>
              <View style={styles.modalView}>
                <Text style={{ color: 'black' }}>Modelos de BarCode</Text>

                <FlatList style={styles.itemList}
                  data={[{ key: "UPC-A" },
                  { key: "UPC-E" },
                  { key: "EAN13" },
                  { key: "EAN8" }]}
                  renderItem={({ item }) => <Text>{item.key}</Text>} />



                <TouchableHighlight
                  style={styles.botao}
                  onPress={() => {
                    setModalVisible(!modalVisible);
                  }}>
                  <Text style={{ color: 'white' }}>CANCEL</Text>
                </TouchableHighlight>
              </View>
            </View>
          </Modal>


        <View>
          <Text></Text>
        </View>

        <TouchableHighlight onPress={() => {
          Alert.alert("Insira o conteúdo do código de barras", "1234567890987654" [{ text: "Cancel", onPress: () => console.log("Cancel Pressed"), style: "cancel" },
            { text: "Ok", onPress: () => console.log("OK Pressed ") }]);
        }}>
          <View>
            <Text style={styles.tamanho}>BarCode
              <Icon name="chevron-right" style={styles.iconConfig} /><TextInput placeholder={"aqui"}> </TextInput></Text>
          </View>
        </TouchableHighlight>

        <View
          style={{
            borderBottomColor: 'white',
            borderBottomWidth: 1,
          }} />

        <TouchableHighlight onPress={() => { setModalVisible(!modalVisible); }}>
          <View>
            <Text style={styles.tamanho}>Modelos de BarCode
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>

        <View
          style={{
            borderBottomColor: 'white',
            borderBottomWidth: 1,
          }} />

        <TouchableHighlight onPress={() => {
          Alert.alert("HRI Posição", "Informe um texo", [
            { text: "Cancel", onPress: () => console.log("Cancel Pressed"), style: "cancel" }
          ]);
        }}>
          <View>
            <Text style={styles.tamanho}>HRI posição
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>

        <View
          style={{
            borderBottomColor: 'white',
            borderBottomWidth: 1,
          }} />

        <TouchableHighlight onPress={() => { alert('Clicou na altura'); }}>
          <View>
            <Text style={styles.tamanho}>Altura
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>
    

        <View
          style={{
            borderBottomColor: 'white',
            borderBottomWidth: 1,
          }} />

        <TouchableHighlight onPress={() => { Alert.alert('Clicou na largura'); }}>
          <View>
            <Text style={styles.tamanho}>Largura
              <Icon name="chevron-right" style={styles.iconConfig} /></Text>
          </View>
        </TouchableHighlight>

        <View
          style={{
            borderBottomColor: 'white',
            borderBottomWidth: 1,
          }} />


        <View style={{ flexDirection: 'row' }}>
          <Text style={styles.tamanho}>Testes </Text>

            
              <TextInput 
              autoCapitalize="none"
              keyboardType='default'
              autoCorrect={false}
              value={codigoTests}
              placeholder='Modelos a serem escolhidos'
              placeholderTextColor='gray'
              textAlign='center'
              onChange={setCodigoTeste} />
              <TouchableHighlight onPress={() => { setModalVisible(!modalVisible); }}>
              <Icon name="chevron-right" style={styles.iconConfig} />

            </TouchableHighlight>
          
        </View>
        <View
          style={{
            borderBottomColor: 'white',
            borderBottomWidth: 1,
          }} />


        <View style={styles.positionImage}>
          <Image
            source={require("../images/logo_tectoy_sunmi.png")} />
        </View>

        <View>
          <Text></Text>
        </View>

        <View style={styles.positionButton}>
          <Button
            onPress={() => Alert.alert('Clicou na impressão')}
            title="Imprimir" />
        </View>



      </SafeAreaView>
    </ScrollView>
  );
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

  corLinha: {
    color: 'white'
  },

  tamanho: {
    justifyContent: "center",
    alignItems: "center",
    fontSize: 25,
    color: 'white'
  },

  positionButton: {
    justifyContent: 'flex-end',
    marginBottom: 36
  },

  positionImage: {
    justifyContent: 'center',
    alignItems: 'center'
  },

  centeredView: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 30

  },
  modalView: {
    height: 400,
    width: 300,
    margin: 20,
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 50,
    alignItems: 'center',
    shadowColor: '#000',

  },

  botao: {
    backgroundColor: 'red',
    position: 'absolute',
    bottom: 15,
    right: 15,
    height: 50,
    width: 80,
    justifyContent: 'center',
    alignItems: 'center'

  },
  itemList: {
    flex: 1,
    padding: 10,
    fontSize: 18,
    height: 44,

  },

  textInput: {
    color: 'green',
   },

})
