import instance from '../../axios/axios';
import {
    PLATFORM_SETTINGS_LOADED,
    PLATFORM_SETTINGS_LOADED_ERROR,
    PLATFORM_SETTINGS_SAVE
} from "./actionTypes";


export const settingsLoaded = (settings) => {
    return {
        type: PLATFORM_SETTINGS_LOADED,
        platformSettings: settings,
    }
};

export const settingsFailedToLoad = (err) => {
    return {
        type: PLATFORM_SETTINGS_LOADED_ERROR,
        platformSettingsError: err,
    }
};


export const saveUserPlatformSettings = (username) => {
    return {
        type: PLATFORM_SETTINGS_SAVE,
        username: username,
    }
};

export const fetchUserPlatformSettings = (username) => {
    return dispatch => {
        instance.get("/user/settings/" + username)
            .then(response => {
                dispatch(settingsLoaded(response.data.settings));
            })
            .catch(err => {
                dispatch(settingsFailedToLoad(err));
            });
    }
};
