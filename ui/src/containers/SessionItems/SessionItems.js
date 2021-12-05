import React from 'react';
import {connect} from 'react-redux';

import ExitToAppIcon from '@material-ui/icons/ExitToApp';

import NavigationItem from '../../comps/Navbar/NavigationItems/NavigationItem/NavigationItem';
import classes from './SessionItems.module.css'

const SessionItems = props => {
    return (
        <ul className={classes.SessionItems}>
            <NavigationItem link="/settings/umgmt" disabled>
                {props.username}
            </NavigationItem>
            <NavigationItem link="/logout" exact>
                <ExitToAppIcon/>
            </NavigationItem>
        </ul>
    );
};

const mapStateToProps = state => {
    return {
        username: state.auth.username
    }
};

export default connect(mapStateToProps)(SessionItems);
