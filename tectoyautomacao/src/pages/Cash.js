import React from "react";
import {Text, View, StyleSheet} from 'react-native';


export default function Cash(){
  return(
      <View style={styles.backgroudScreen}>
          <Text>Esse é o Cash component</Text>
      </View>
  )
}

const styles = StyleSheet.create({
  backgroudScreen:{
    backgroundColor: '#000',

  }
});