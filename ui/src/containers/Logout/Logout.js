import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {connect} from 'react-redux';

import * as actions from '../../store/actions/index';

const Logout = ({onLogout, savePlatformSettings}) => {

    useEffect(() => {
        console.log('whatever')
        savePlatformSettings(localStorage.getItem('username'));
        onLogout();
    }, []);

    return <Redirect to="/"/>;
}

const mapDispatchToProps = dispatch => {
    return {
        onLogout: () => dispatch(actions.logout()),
        savePlatformSettings: (username) => dispatch(actions.saveUserPlatformSettings(username)),
    }
}

export default connect(null, mapDispatchToProps)(Logout);
