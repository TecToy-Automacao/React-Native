import * as React from 'react';
import { Portal, Text, View } from 'react-native';

const OutroComponent = () => (
  <Portal>
      <View>
    <Text>This is rendered at a different place</Text>
    </View>
  </Portal>
);

export default OutroComponent;