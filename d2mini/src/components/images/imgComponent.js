import { Image } from "react-native";
import React from "react";

export default function ImgComponent({ source }) {
  return (
    <Image
      style={{
        display: "flex",
        resizeMode: "contain",
        width: "100%",
        height: 120,
        maxWidth: 540,
      }}
      source={source}
    />
  );
}
