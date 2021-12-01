import React, { Component, useState } from 'react';
import { View, Text, StyleSheet, Button, Image, TouchableHighlight, FlatList } from 'react-native';



const [modalVisible, setModalVisible] = useState(false);


class Modal extends Component{


  
  mostraModal(){
  return setModalVisible(!modalVisible);
}

    render(){

        return(
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
            )

    }
    

}
export default Modal;