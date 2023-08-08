import { TextInput } from "react-native";
import React from "react";

export default function Inputs({ value, onChangeText }) {
  return (
    <TextInput
      style={{
        paddingHorizontal: 10,
        display: "flex",
        width: "100%",
        maxWidth: 540,
        height: 40,
        borderWidth: 1,
        borderRadius: 4,
        marginBottom: 10,
      }}
      value={value}
      onChangeText={onChangeText}
    />
  );
}
