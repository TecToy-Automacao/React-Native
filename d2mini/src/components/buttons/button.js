import { Pressable, Text } from "react-native";

export default function Button({ onPress, text }) {
  return (
    <Pressable
      onPress={onPress}
      style={{
        alignItems: "center",
        justifyContent: "center",
        paddingVertical: 12,
        paddingHorizontal: 20,
        borderRadius: 4,
        elevation: 3,
        width: 200,
        backgroundColor: "#841584",
      }}
    >
      <Text
        style={{
          fontSize: 16,
          lineHeight: 21,
          fontWeight: "bold",
          letterSpacing: 0.25,
          color: "white",
        }}
      >
        {text}
      </Text>
    </Pressable>
  );
}
