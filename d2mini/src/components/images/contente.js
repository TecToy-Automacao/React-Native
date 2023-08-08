import { View, Text } from "react-native";
import React from "react";

export default function Contente({ children }) {
  return (
    <View
      style={{
        display: "flex",
        width: "100%",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      {children}
    </View>
  );
}
