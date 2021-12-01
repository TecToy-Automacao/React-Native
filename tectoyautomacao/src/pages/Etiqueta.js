import React from "react";
import {Text, View} from 'react-native';

export default function Etiqueta(){
  return(
      <View>
          <Text>Esse é a Etiqueta component</Text>
      </View>
  )
}
/* 
class Etiqueta extends React.Component {
    
    render() {
        return (
          <View style={styles.container}>
            <Text>Add friends here!</Text>
            <Button
              title="Back to home"
              onPress={() =>
                this.props.navigation.navigate('Home')
              }
            />
          </View>
        );
      }
    } */