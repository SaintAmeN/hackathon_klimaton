import instance from '../../axios/axios';
import * as actionTypes from './actionTypes';
import {fetchUserPlatformSettings} from "./userPlatformSettings";

const authStart = () => {
    return {
        type: actionTypes.AUTH_START
    }
}

export const auth = (username, password) => {
    return dispatch => {
        dispatch(authStart());
        const authData = {
            username: username,
            password: password
        };
        instance.post("/authenticate", authData)
            .then(response => {
                localStorage.setItem('token', response.data.token);
                localStorage.setItem('expirationDate', response.data.expiresAt);
                localStorage.setItem('username', response.data.username);
                dispatch(authSuccess(response.data.token, response.data.username));
                dispatch(fetchUserPlatformSettings(response.data.username));
            })
            .catch(err => {
                dispatch(authFail(err));
            });
    }
}

const authSuccess = (token, username) => {
    return {
        type: actionTypes.AUTH_SUCCESS,
        token: token,
        username: username
    }
}

const authFail = (error) => {
    return {
        type: actionTypes.AUTH_FAIL,
        error: error
    }
}

export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    return {
        type: actionTypes.AUTH_LOGOUT
    }
}

export const checkAuthState = () => {
    return dispatch => {
        const token = localStorage.getItem('token');
        if (!token) {
            dispatch(logout());
        } else {
            const expirationDate = localStorage.getItem('expirationDate')
            if (new Date().getTime() >= expirationDate) {
                dispatch(logout());
            } else {
                const username = localStorage.getItem('username');
                dispatch(authSuccess(token, username));
                dispatch(fetchUserPlatformSettings(username));
                dispatch(checkAuthTimeout(expirationDate))
            }
        }
    }
}

const checkAuthTimeout = (expirationTime) => {
    return dispatch => {
        setTimeout(() => {
            dispatch(logout());
        }, expirationTime - new Date().getTime());
    }
}
