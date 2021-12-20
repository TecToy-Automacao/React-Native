import {createAppContainer} from 'react-navigation';
import {createStackNavigator} from 'react-navigation-stack';

import App from './App';
import Lampada from './pages/lampada';
import Msitef from './pages/msitef';
import QrCode from './pages/QrCode';
import Paygo from './pages/paygo';

const Routes = createStackNavigator(
    {
        Home: {
            screen: App,
            navigationOptions: {
                headerShown: false,
            }
        },
        Qr_CODE: {
            screen: QrCode,
            navigationOptions: {
                headerShown: false,
            }
        },
        Lampada_K2: {
            screen: Lampada,
            navigationOptions: {
                headerShown: false,
            }
        },
        Msitef: {
            screen: Msitef,
            navigationOptions: {
                headerShown: false,
            }
        },
        PAYGO: {
            screen: Paygo,
            navigationOptions: {
                headerShown: false,
            }
        },
    },  
);

export default createAppContainer(Routes);