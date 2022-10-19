import React, { useState } from "react";
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  Image,
  Pressable,
  NativeModules,
} from "react-native";

import aguia from "./assets/itfast.png";
import tectoy from "./assets/tecToy.png";

export default function App() {
  const [text, setText] = useState("Coloque o texto aqui....");
  const { It4rModule } = NativeModules;
  const onPress = () => {
    It4rModule.printEvent(text);
  };

  return (
    <View
      style={[
        styles.container,
        { flexDirection: "column", backgroundColor: "#c1c1c1" },
      ]}
    >
      <View style={{ flex: 1 }}>
        <Image source={aguia} />
      </View>
      <View style={{ flex: 2, alignItems: "center", justifyContent: "center" }}>
        <View
          style={{
            flexDirection: "row",
            padding: 10,
            justifyContent: "center",
          }}
        >
          <View style={styles.viewBtn}>
            <TextInput
              style={{
                minWidth: 400,
                height: 40,
                borderWidth: 1,
                marginBottom: 10,
              }}
              value={text}
              onChangeText={setText}
            />
            <Pressable color="#841584" onPress={onPress} style={styles.button}>
              <Text style={styles.text}>IMPRESSÃO</Text>
            </Pressable>
          </View>
        </View>
      </View>
      <View style={{ flex: 1, alignItems: "center" }}>
        <Image source={tectoy} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  button: {
    alignItems: "center",
    justifyContent: "center",
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 4,
    elevation: 3,
    width: 200,
    backgroundColor: "#841584",
  },
  text: {
    fontSize: 16,
    lineHeight: 21,
    fontWeight: "bold",
    letterSpacing: 0.25,
    color: "white",
  },
  viewBtn: {
    flex: 1,
    margin: 10,
    alignItems: "center",
  },
});
