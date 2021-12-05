import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {connect} from 'react-redux';
import classes from './App.module.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Layout from './components/hoc/Layout/Layout';
import * as actions from './store/actions/index'
import AppContent from "./components/content/AppContent";
import AuthContent from "./components/content/AuthContent";

class App extends Component {
    componentDidMount() {
        this.props.checkAuthState();
    }

    render() {
        return (
            <div className={classes.App}>
                <Layout>
                    {this.props.isAuthenticated ? (<AppContent/>) : (<AuthContent/>) }
                </Layout>
            </div>
        );
    }
}

const mapStateToProps = state => {
        return {
            isAuthenticated: state.auth.token !== null
        };
    }
;

const mapDispatchToProps = dispatch => {
        return {
            checkAuthState: () => dispatch(actions.checkAuthState())
        }
    }
;

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));
