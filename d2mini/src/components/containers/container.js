import { View } from "react-native";

export default function Container({ children }) {
  return (
    <View
      style={{
        flex: 2,
        width: "100%",
        paddingHorizontal: 12,
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      {children}
    </View>
  );
}
