import { StatusBar } from 'expo-status-bar';
import React from 'react';

import { NavigationContainer } from '@react-navigation/native';
import Routes from './src/routes';

export default function App() {
  return (
    // <View style={styles.container}>
    //   <ImageBackground source={require('./assets/entrarfarmacia.jpeg')} resizeMode='cover' style={styles.image}>
    //     <StatusBar style='auto' />
    //   </ImageBackground>
    // </View>
    <>
      <StatusBar backgroundColor='#008aab' barStyle='light-content' />
      <NavigationContainer>
        <Routes />
        {/* <Toast /> */}
      </NavigationContainer>
    </>
  );
}


