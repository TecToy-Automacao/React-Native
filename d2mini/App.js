import React, { useState } from "react";
import { NativeModules } from "react-native";

import aguia from "./assets/itfast.png";
import tectoy from "./assets/tecToy.png";
import RootLayout from "./src/components/layouts/rootLayout";
import CustomImage from "./src/components/images/customImage";
import Button from "./src/components/buttons/button";
import Inputs from "./src/components/inputs/inputs";
import Container from "./src/components/containers/container";

export default function App() {
  const [text, setText] = useState("Coloque o texto aqui....");
  const { It4rModule } = NativeModules;
  const onPress = () => {
    It4rModule.printEvent(text);
  };

  return (
    <RootLayout>
      <CustomImage source={aguia} />
      <Container>
        <Inputs value={text} onChangeText={setText} />
        <Button onPress={onPress} text={"IMPRESSÃO"} />
      </Container>
      <CustomImage source={tectoy} />
    </RootLayout>
  );
}
