import React from 'react';
import classes from "./AppHeader.module.css";
import {Link} from 'react-router-dom';

import CardGiftcard from '@material-ui/icons/CardGiftcard'
import PeopleAlt from '@material-ui/icons/PeopleAlt'
import ExitToApp from '@material-ui/icons/ExitToApp'

import logo from "../../assets/images/logo.svg";
import {connect} from "react-redux";

const HEADER_BUTTONS_AUTHENTICATED = [
    {
        name: 'Logout',
        href: '/logout',
        icon: (<ExitToApp/>),
    },
    {
        name: 'Profile',
        href: '/profile',
        icon: (<PeopleAlt fontSize={"small"}/>),
    },
    {
        name: 'Achievements',
        href: '/achievements',
        icon: (<CardGiftcard fontSize={"small"}/>),
    },
    {
        name: 'Home',
        href: '/home',
        icon: (<></>),
    },
]
const HEADER_BUTTONS_UNAUTHENTICATED = [
    {
        name: 'Login',
        href: '/login',
        icon: (<></>),
    },
    {
        name: 'Home',
        href: '/home',
        icon: (<></>),
    },
]

const AppHeader = ({isAuthenticated}) => {

    const mapToHeaderButton = (buttonInfo) => {
        return (
            <Link key={buttonInfo.name} to={buttonInfo.href} className={classes.HeaderMenuButton}>
                {buttonInfo.icon}
                <div>{buttonInfo.name}</div>
            </Link>
        )
    }

    return (
        <header className={classes.AppHeader}>
            <div className={classes.HeaderLeft}>
                <div className={classes.AppLogoContainer}>
                    <img src={logo} className={classes.AppLogo} alt="logo"/>
                </div>
            </div>
            <div className={classes.HeaderRight}>
                {
                    isAuthenticated ?
                        (HEADER_BUTTONS_AUTHENTICATED.map(mapToHeaderButton)) :
                        ((HEADER_BUTTONS_UNAUTHENTICATED.map(mapToHeaderButton)))
                }
            </div>
        </header>
    );
}

const mapStateToProps = state => {
    return {
        isAuthenticated: state.auth.token !== null
    };
};

export default connect(mapStateToProps)(AppHeader);