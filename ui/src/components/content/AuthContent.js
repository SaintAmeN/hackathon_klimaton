import React from 'react';
import classes from './AppContent.module.css'
import {Route, Switch} from "react-router-dom";
import ContentHome from "./home/ContentHome";
import Auth from "../../containers/Auth/Auth";

const AuthContent = () => {
    return (
        <div className={classes.AppContent}>
            <Switch>
                <Route path={'/home'}>
                    <ContentHome/>
                </Route>
                <Route path={"/login"}>
                    <Auth/>
                </Route>
                <Route path={"/"}>
                    <Auth/>
                </Route>
            </Switch>
        </div>
    )
}

export default AuthContent;