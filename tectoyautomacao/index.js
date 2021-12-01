/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './src/App';
import {name as appName} from './app.json';
import Routes from './src/Routes';

AppRegistry.registerComponent(appName, () => Routes);
// console.disableYellowBox = true;