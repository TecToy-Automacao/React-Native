import { View } from "react-native";

export default function RootLayout({ children }) {
  return (
    <View
      style={{
        flex: 1,
        alignItems: "center",
        flexDirection: "column",
        backgroundColor: "#c1c1c1",
        paddingTop: 20,
        paddingBottom: 10,
      }}
    >
      {children}
    </View>
  );
}
