import React, { Component } from "react";
import { Text, View, StyleSheet, Button, Alert, TextInput, NativeModules, DeviceEventEmitter } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";
import { Picker } from '@react-native-picker/picker';
import Icon from 'react-native-vector-icons/FontAwesome';
import OutroComp from "../components/OutroComp";



var Exemplo = NativeModules.Exemplo;
var ToastExample = NativeModules.ToastExample;

state = {
  toggle: true,
  toggle2: true,
  toggle3: true,
}

var hobbies = [
  { label: 'Esquerda', value: 'Esquerda' },
  { label: 'Centralizado', value: 'Centro' },
  { label: 'Direita', value: 'Direita' }
];



export default class Formulario extends Component {
  constructor(props) {
    super(props);
    this.state = { text: '', statusPrint: false };

  }

  async componentDidMount() {
    DeviceEventEmitter.addListener("eventStatus", event => {
      this.setState({ statusPrint: event.status })
    }
    );
  }

  imprimeTexto(text, language1, language2, toggle, toggle2, toggle3, alinhamento) {

    console.log("XXXXXX: ", language1);

    if (language1 == null) { language1 = "DEFAULT" };
    if (language2 == null) { language2 = 10 };
    if (toggle == null) { toggle = false };
    if (toggle2 == null) { toggle2 = false };
    if (toggle3 == null) { toggle3 = false }
    if (alinhamento == null) { alinhamento = "CENTER" };

    if (text == null || text == '') {
      Alert.alert('Preencha o texto')
      if (text == '') text = null;
    }

    if (text == null) {
      ToastExample.imprimeTexto(text, language1, parseInt(language2, 10), toggle, toggle2, toggle3, alinhamento);
      ToastExample.fimImpressao();
    }
  }

  testImpressao() {
    Exemplo.testImpressao();
    Exemplo.fimImpressao();

  }

  imprimeTudo() {
    console.log("loglog");
    ToastExample.imprimeTudo();
    ToastExample.fimImpressao();
  };

  _onPress() {
    const newState = !this.state.toggle;
    this.setState({ toggle: newState })
  }



  render() {

    const { toggle } = this.state;
    const { toggle2 } = this.state;
    const { toggle3 } = this.state;

    const borderC = toggle ? "blue" : "white";

    const textColor = toggle ? "black" : "white";
    const borderC2 = toggle2 ? "blue" : "white";

    const textColor2 = toggle2 ? "black" : "white";
    const borderC3 = toggle3 ? "blue" : "white";

    const textColor3 = toggle3 ? "black" : "white";
    return (

      <SafeAreaView style={styles.backgroudScreen}>
        <View style={styles.backgroudScreen}>
          <Text style={styles.texto}>Row1</Text>


          <View style={styles.linha}>
            <Text style={styles.textoDois}>Text</Text>
            <TextInput style={styles.input}
              placeholder='Texto'
              placeholderTextColor="#FFF"
              value={OutroComp.textUm}
              onChangeText={() => OutroComp.setTextUm}
            />
            <TextInput style={styles.input}
              placeholder='Texto'
              placeholderTextColor="#FFF"
              value={OutroComp.textDois}
              onChangeText={OutroComp.setTextDois}
            />
            <TextInput style={styles.input}
              placeholder='Texto'
              placeholderTextColor="#FFF"
              value={OutroComp.textTres}
              onChangeText={() => OutroComp.setTextTres}
            />
          </View>

          <View style={styles.linhaDois}>
            <Text style={styles.textoDois}>Weeight</Text>
            <TextInput style={styles.input}
              placeholder=' 50'
              placeholderTextColor="#FFF"
              value={OutroComp.number}
              onChangeText={() => OutroComp.setNumber}
            />
            <TextInput style={styles.input}
              placeholder=' 50'
              placeholderTextColor="#FFF"
              value={OutroComp.numero}
              onChangeText={() => OutroComp.setNumero}
            />
            <TextInput style={styles.input}
              placeholder=' 50'
              placeholderTextColor="#FFF"
              value={OutroComp.numberUm}
              onChangeText={() => OutroComp.setNumberUm}
            />

          </View>


          <View style={styles.linhaTres}>
            <Text style={styles.textoDois}>Alling</Text>
            <Picker
              style={styles.tamanhoPicker}
              selectedValue={OutroComp.selectedValue}
              onValueChange={(itemValue, itemIndex) => OutroComp.setSelectedValue(itemValue)}
            >
              <Picker.Item style={styles.itemPicker} label="Esquerda" value="Esquerda" />
              <Picker.Item style={styles.itemPicker} label="Direita" value="Direita" />
              <Picker.Item style={styles.itemPicker} label="Centro" value="Centro" />
            </Picker>
            <Icon
              name='sort-down'
              size={20}
              color='gray'
              style={styles.icon} />


            <View>
              <Picker
                style={styles.tamanhoPicker}
                selectedValue={OutroComp.valorEscolhido}
                onValueChange={(itemValue, itemIndex) => mudarValor.setSelectedValue.itemValue}
              >
                <Picker.Item style={styles.itemPicker} label="Esquerda" value="Esquerda" />
                <Picker.Item style={styles.itemPicker} label="Direita" value="Direita" />
                <Picker.Item style={styles.itemPicker} label="Centro" value="Centro" />
              </Picker>
              <Icon
                name='sort-down'
                size={20}
                color='gray'
                style={styles.icon} />


              <View>
                <Picker
                  style={styles.tamanhoPicker}
                  selectedValue={OutroComp.valorSelecionado}
                  onValueChange={(itemValue, itemIndex) => setValorSelecionado(itemValue)}
                >
                  <Picker.Item style={styles.itemPicker} label="Esquerda" value="Esquerda" />
                  <Picker.Item style={styles.itemPicker} label="Direita" value="Direita" />
                  <Picker.Item style={styles.itemPicker} label="Centro" value="Centro" />
                </Picker>
                <Icon
                  name='sort-down'
                  size={20}
                  color='gray'
                  style={styles.icon} />
              </View>

            </View>


          </View>


          <View >
            <Button
              backgroundColor='white'
              color='green'
              title="Adicionar"
              onPress={() => Alert.alert('Apertou o botão adicionar')}
            ></Button>
          </View>

          <View style={styles.botao}>
            <Button
              color='red'
              title="Imprimir"
              onPress={() => alert("Clicou no botão imprimir")} />
          </View>
        </View>
      </SafeAreaView>
    )

  }

  /* export default function Formulario(){
  
 
   const [textUm, setTextUm] = React.useState('');
   const [textDois, setTextDois] = React.useState('');
   const [textTres, setTextTres] = React.useState('');
   const [number, setNumber] = React.useState('');
   const [numero, setNumero] = React.useState('');
   const [numberUm, setNumberUm] = React.useState('');
   const [selectedValue, setSelectedValue] = React.useState('');
   const [valorSelecionado, setValorSelecionado] = React.useState('');
   const [valorEscolhido, setValorEscolhido] = React.useState('');
 
 
   return (
 
 
 
     <SafeAreaView style={styles.backgroudScreen}>
       <View style={styles.backgroudScreen}>
         <Text style={styles.texto}>Row1</Text>
 
 
         <View style={styles.linha}>
           <Text style={styles.textoDois}>Text</Text>
           <TextInput style={styles.input}
             placeholder='Texto'
             placeholderTextColor="#FFF"
             value={textUm}
             onChangeText={text => setTextUm(text)}
           />
           <TextInput style={styles.input}
             placeholder='Texto'
             placeholderTextColor="#FFF"
             value={textDois}
             onChangeText={text => setTextDois(text)}
           />
           <TextInput style={styles.input}
             placeholder='Texto'
             placeholderTextColor="#FFF"
             value={textTres}
             onChangeText={text => setTextTres(text)}
           />
         </View>
 
         <View style={styles.linhaDois}>
           <Text style={styles.textoDois}>Weeight</Text>
           <TextInput style={styles.input}
             placeholder=' 50'
             placeholderTextColor="#FFF"
             value={number}
             onChangeText={number => setNumber(number)}
           />
           <TextInput style={styles.input}
             placeholder=' 50'
             placeholderTextColor="#FFF"
             value={numero}
             onChangeText={number => setNumero(number)}
           />
           <TextInput style={styles.input}
             placeholder=' 50'
             placeholderTextColor="#FFF"
             value={numberUm}
             onChangeText={number => setNumberUm(number)}
           />
 
         </View>
 
 
         <View style={styles.linhaTres}>
           <Text style={styles.textoDois}>Alling</Text>
           <Picker
             style={styles.tamanhoPicker}
             selectedValue={selectedValue}
             onValueChange={(itemValue, itemIndex) => setSelectedValue(itemValue)}
           >
             <Picker.Item style={styles.itemPicker} label="Esquerda" value="Esquerda" />
             <Picker.Item style={styles.itemPicker} label="Direita" value="Direita" />
             <Picker.Item style={styles.itemPicker} label="Centro" value="Centro" />
           </Picker>
           <Icon
             name='sort-down'
             size={20}
             color='gray'
             style={styles.icon} />
 
 
           <View>
             <Picker
               selectedValue={valorSelecionado}
               style={styles.tamanhoPicker}
               onValueChange={(itemValue, itemIndex) => setValorSelecionado(itemValue)}
             >
               <Picker.Item style={styles.itemPicker} label="Esquerda" value="Esquerda" />
               <Picker.Item style={styles.itemPicker} label="Direita" value="Direita" />
               <Picker.Item style={styles.itemPicker} label="Centro" value="Centro" />
             </Picker>
             <Icon
               name='sort-down'
               size={20}
               color='gray'
               style={styles.icon} />
           </View>
 
           <View>
             <Picker
               selectedValue={valorEscolhido}
               style={styles.tamanhoPicker}
               onValueChange={(itemValue, itemIndex) => setValorEscolhido(itemValue)}
             >
               <Picker.Item style={styles.itemPicker} label="Esquerda" value="Esquerda" />
               <Picker.Item style={styles.itemPicker} label="Direita" value="Direita" />
               <Picker.Item style={styles.itemPicker} label="Centro" value="Centro" />
             </Picker>
           </View>
 
         </View>
 
 
       </View>
 
 
       <View >
         <Button
           backgroundColor='white'
           color='green'
           title="Adicionar"
           onPress={() => Alert.alert('Apertou o botão adicionar')}
         ></Button>
       </View>
 
 
       <View style={styles.botao}>
         <Button
           color='red'
           title="Imprimir"
           onPress={ ()=>  Alert.alert('Apertou o botão imprimir')} />
       </View>
     </SafeAreaView>
   )
 }  */
}

const styles = StyleSheet.create({
  backgroudScreen: {
    flex: 1,
    backgroundColor: '#000',
  },

  texto: {
    color: 'gray',
    marginLeft: 15,
    marginTop: 20,
    fontSize: 25,
  },

  textoDois: {
    color: 'white',
    marginLeft: 15,
    marginTop: 20,
    fontSize: 20,
  },

  botao: {
    marginBottom: 20,
    marginTop: 15
  },

  input: {
    height: 40,
    textAlign: 'center',
    color: 'white',

  },

  linha: {
    flexDirection: 'row',
    justifyContent: 'space-between'

  },

  linhaDois: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    borderColor: 'gray',


  },

  linhaTres: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignContent: 'center',
    alignItems: 'center',


  },

  tamanhoPicker: {
    height: 50,
    width: 100,

  },

  icon: {
    right: 18,
    position: 'absolute'
  },
  itemPicker: {
    color: 'red',


  }

});
