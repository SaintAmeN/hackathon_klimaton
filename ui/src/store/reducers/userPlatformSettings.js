import * as actionTypes from '../actions/actionTypes';
import {updateObject} from '../../shared/utility';

import cloneDeep from 'lodash/cloneDeep';
import PlatformSettings from "../../containers/PlatformSettings/PlatformSettings";
import instance from "../../axios/axios";
import {updateUserSettings} from '../../shared/localStorage';

const initialPlatformSettings = () => {
    const newPlatformSettings = cloneDeep(PlatformSettings);
    newPlatformSettings.load(null);
    return newPlatformSettings;
}

const initialState = {
    platformSettings: initialPlatformSettings()
}

const userPlatformSettingsLoaded = (state, action) => {
    const newPlatformSettings = cloneDeep(state.platformSettings);
    newPlatformSettings.load(action.platformSettings);

    return updateObject(state, {
        platformSettings: newPlatformSettings
    });
}

// eslint-disable-next-line no-unused-vars
const userPlatformSettingsLoadedError = (state, action) => {
    // TODO: zrobic notyfikacje powodu/zaladowania ustawien : SOFT-1285
    return updateObject(state, {
        platformSettings: PlatformSettings
    });
}


const userPlatformSettingsSaved = (state, action) => {
    const updateDto = {
        'settings': JSON.stringify(state.platformSettings.settings)
    }

    instance.patch("/user/settings/" + action.username, updateDto)
        .catch(err => {
            console.error('Settings not saved: ' + err);
        });

    return updateObject(state, {
        platformSettings: initialPlatformSettings()
    });
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.PLATFORM_SETTINGS_SAVE:
            return userPlatformSettingsSaved(state, action);
        case actionTypes.PLATFORM_SETTINGS_LOADED:
            return userPlatformSettingsLoaded(state, action);
        case actionTypes.PLATFORM_SETTINGS_LOADED_ERROR:
            return userPlatformSettingsLoadedError(state, action);
        default:
            return state;
    }
}

export default reducer;
