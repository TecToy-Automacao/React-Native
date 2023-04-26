import React, { useRef, useState, useEffect } from 'react';
import {
  View,
  StyleSheet,
  ImageBackground,
  TextInput,
  Text,
  TouchableWithoutFeedback,
  // NativeModules,
} from 'react-native';
import Pdf from 'react-native-pdf';

import { useNavigation } from '@react-navigation/native';

// import de imagens
const flanax = require('../../assets/flanax.jpeg');
const candicort = require('../../assets/candicort.jpeg');
const desodorante = require('../../assets/desodorante.jpeg');
const dipirona = require('../../assets/dipirona.jpeg');
// const esmalte = require('../../assets/esmalte.jpeg');
const rivotril = require('../../assets/rivotril.jpeg');
const shampoo = require('../../assets/shampoo.jpeg');

// import bullas
const flanaxB = { uri: 'bundle-assets://flanax.pdf' };
const candicortB = { uri: 'bundle-assets://candicort.pdf' };
const desodoranteB = { uri: 'bundle-assets://desodorante.pdf' };
const dipironaB = { uri: 'bundle-assets://dipirona.pdf' };
const rivotrilB = { uri: 'bundle-assets://rivotril.pdf' };
const shampooB = { uri: 'bundle-assets://shampoo.pdf' };

export default function Dashboard() {
  const navigation = useNavigation();
  const myRef = useRef(null);
  const reloadFocus = useRef(null);

  const [text, onChangeText] = useState('');
  const [visible, setVisible] = useState(true);
  const [remedyImg, setRemedyImg] = useState(desodorante);
  const [price, setPrice] = useState('');
  const [stoke, setStoke] = useState('');
  const [source, setSource] = useState(dipironaB);
  const [spec, setSpec] = useState('');
  const [textSpec, setTextSpec] = useState('VENDA COM RECEITA E RESTRIÇÃO');

  // const { It4rModule } = NativeModules;

  const setFocus = () => {
    if (myRef?.current) {
      myRef.current.focus();
    }
  };

  useEffect(() => {
    reloadFocus.current = setInterval(setFocus, 2 * 1000);
    return () => {
      clearInterval(reloadFocus.current);
    };
  }, []);

  useEffect(() => {
    if (text === '7891106006507') {
      setRemedyImg(flanax);
      setSpec('');
      setPrice('27,76');
      setStoke('38');
      setSource(flanaxB);
      setVisible(true);
    } else if (text === '7896658012181') {
      setRemedyImg(candicort);
      setSpec('candicort');
      setTextSpec('VENDA SOB RESCRIÇÃO MÉDICA');
      setPrice('35,86');
      setStoke('15');
      setSource(candicortB);
      setVisible(true);
    } else if (text === '7896226504742') {
      setRemedyImg(rivotril);
      setSpec('rivotril');
      setTextSpec('VENDA COM RECEITA E RESTRIÇÃO');
      setPrice('7,67');
      setStoke('5');
      setSource(rivotrilB);
      setVisible(true);
    } else if (text === '7891024174128') {
      setRemedyImg(shampoo);
      setSpec('');
      setPrice('12,26');
      setStoke('14');
      setSource(shampooB);
      setVisible(true);
    } else if (text === '794003011612') {
      setRemedyImg(desodorante);
      setSpec('');
      setPrice('116,69');
      setStoke('45');
      setSource(desodoranteB);
      setVisible(true);
    } else if (text === '7896004715841') {
      setRemedyImg(dipirona);
      setSpec('dipirona');
      setTextSpec('MEDICAMENTO GENÉRICO');
      setPrice('9,83');
      setStoke('25');
      setSource(dipironaB);
      setVisible(true);
    }
    onChangeText('');
  }, [text]);

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      width: '100%',
      alignItems: 'center',
      justifyContent: 'center',
    },
    product: {
      position: 'absolute',
      backgroundColor: '#fff',
      borderRadius: 30,
      paddingVertical: 8,
      width: 650,
      alignSelf: 'center',
      height: 475,
      left: 20,
      top: 205,
      alignItems: 'center',
      justifyContent: 'center',
    },
    pdf: {
      position: 'absolute',
      backgroundColor: '#fff',
      borderRadius: 30,
      padding: 10,
      width: 613,
      height: 475,
      right: 24,
      bottom: 25,
      alignItems: 'center',
      justifyContent: 'center',
    },
    image: {
      width: '100%',
      flex: 1,
      justifyContent: 'center',
    },
    input: {
      position: 'absolute',
      top: 10,
      height: 40,
      width: 400,
      margin: 12,
      borderWidth: 1,
      right: 1,
      padding: 10,
      borderColor: 'transparent',
      color: 'transparent',
    },
    text: {
      position: 'absolute',
      top: 15,
      margin: 'auto',
      borderWidth: 1,
      borderColor: 'transparent',
      fontSize: 30,
      color: 'red',
      fontWeight: 'bold',
    },
    receita: {
      position: 'absolute',
      // transform: [{ rotate: '-35deg'}],
      backgroundColor:
      spec === 'dipirona' ? 'yellow' : spec === 'rivotril' ? '#000' : 'red',
      paddingVertical: 8,
      width: 650,
      alignSelf: 'center',
      textAlign: 'center',
      opacity: 0.5,
      textShadowColor: '#000',
      left: 20,
      top: 610,
      fontSize: 30,
      color: spec === 'dipirona' ? '#000' : '#fff',
      fontWeight: 'bold',
      alignItems: 'center',
      justifyContent: 'center',
    },
    goback: {
      position: 'absolute',
      paddingVertical: 8,
      alignSelf: 'center',
      textAlign: 'center',
      textShadowColor: '#000',
      left: 25,
      top: 320,
      fontSize: 160,
      color: '#000',
      alignItems: 'center',
      justifyContent: 'center',
    },
    inform: {
      position: 'absolute',
      paddingVertical: 8,
      width: 650,
      alignSelf: 'center',
      textAlign: 'center',
      opacity: 0.5,
      textShadowColor: '#000',
      right: 20,
      top: 420,
      fontSize: 30,
      color: '#000',
      fontWeight: 'bold',
      alignItems: 'center',
      justifyContent: 'center',
    },
    textPrice: {
      position: 'absolute',
      top: 55,
      margin: 'auto',
      borderWidth: 1,
      borderColor: 'transparent',
      fontSize: 60,
      color: 'red',
      fontWeight: 'bold',
    },
    button: {
      position: 'absolute',
      backgroundColor: '#008aab',
      borderRadius: 50,
      paddingVertical: 8,
      width: '30%',
      alignSelf: 'center',
      height: '60%',
      bottom: '20%',
      alignItems: 'center',
      justifyContent: 'center',
    },
    buttonText: {
      fontSize: 18,
      color: '#fff',
      fontWeight: 'bold',
    },
  });

  return (
    <View style={styles.container}>
      <ImageBackground
        source={require('../../assets/farmacia.jpeg')}
        resizeMode='cover'
        style={styles.image}
      />
      <TextInput
        ref={myRef}
        style={styles.input}
        showSoftInputOnFocus={false} //oculta teclado
        selectionColor='transparent'
        onChangeText={onChangeText}
        value={text}
      />
      {visible && (
        <>
          <Text style={styles.text}>QUANTIDADE: {stoke} UN</Text>
          <Text style={styles.textPrice}>R${price}</Text>

          <TouchableWithoutFeedback onPress={() => setVisible(false)}>
            <ImageBackground
              source={remedyImg}
              resizeMode='center'
              style={styles.product}
            />
          </TouchableWithoutFeedback>
          {spec !== '' && <Text style={styles.receita}>{textSpec}</Text>}
          <TouchableWithoutFeedback onPress={() => setVisible(false)}>
            <Text style={styles.goback}>{'<'}</Text>
          </TouchableWithoutFeedback>
          <Pdf
            trustAllCerts={false}
            source={source}
            style={styles.pdf}
            scale={1.5}
          />
        </>
      )}
      {!visible && (
        <Text style={styles.inform}>
          Passe o código de barras no leitor para pesquisar
        </Text>
      )}
    </View>
  );
}
