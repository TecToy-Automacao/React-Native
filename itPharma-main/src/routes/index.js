import { createNativeStackNavigator } from '@react-navigation/native-stack'

import Login from '../screens/Login'
import Dashboard from '../screens/Dashboard';

// const {Navigator, Screen} = createNativeStackNavigator();
const Stack = createNativeStackNavigator();

export default function Routes() {
  return (
    <Stack.Navigator screenOptions={{headerShown: false}}>
      <Stack.Screen
      name='Login'
      component={Login}
      />
      <Stack.Screen
      name='Dashboard'
      component={Dashboard}
      />
    </Stack.Navigator>
  )
}