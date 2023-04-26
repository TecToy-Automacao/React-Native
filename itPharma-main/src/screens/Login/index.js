import React from 'react';
import { View, StyleSheet, ImageBackground, TouchableOpacity, Text } from 'react-native'
import { useNavigation } from '@react-navigation/native'

export default function Login(){
  const navigation = useNavigation();
  return(
    <View style={styles.container}>
      <ImageBackground source={require('../../assets/entrarfarmacia.jpeg')} resizeMode='cover' style={styles.image} />
      <TouchableOpacity  style={styles.button} onPress={() => navigation.navigate('Dashboard')} />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: '100%',
    // backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  image: {
    width: '100%',
    flex: 1,
    justifyContent: 'center',
  },
  button:{
    position: 'absolute',
    // backgroundColor: '#008aab',
    borderRadius: 50,
    paddingVertical: 8,
    width: '30%',
    alignSelf: 'center',
    height: '60%',
    bottom: '20%',
    alignItems: 'center',
    justifyContent: 'center'
  },
  buttonText: {
    fontSize: 18,
    color: '#fff',
    fontWeight: 'bold',
  }
});