import React from 'react';
import classes from './AppContent.module.css'
import {Route, Switch} from "react-router-dom";
import ContentHome from "./home/ContentHome";
import ContentProfile from "./profile/ContentProfile";
import Logout from "../../containers/Logout/Logout";
import ContentAchievements from "./achievements/ContentAchievements";

const AppContent = () => {
    return (
        <div className={classes.AppContent}>
            <Switch>
                <Route path={'/profile'}>
                    <ContentProfile/>
                </Route>
                <Route path={'/logout'}>
                    <Logout/>
                </Route>
                <Route path={'/achievements'}>
                    <ContentAchievements/>
                </Route>
                <Route path={'/family'}>
                    <ContentProfile/>
                </Route>
                <Route path={'/'}>
                    <ContentHome/>
                </Route>
            </Switch>
        </div>
    )
}

export default AppContent;