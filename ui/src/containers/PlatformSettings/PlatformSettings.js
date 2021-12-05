import React from 'react';
import {DefaultPlatformSettings} from "./DefaultPlatformSettings";
import {getUserSettings} from '../../shared/localStorage';

export const SECTION_BASIC = 'basic';

export const SettingsCategories = [
    SECTION_BASIC
];

/**
 * This class is a representation of user platform settings.
 */
export const PlatformSettings = {
    load(loadedSettings) {
        if (loadedSettings) {
            this.settings = JSON.parse(loadedSettings);
        } else {
            this.settings = {};
        }

        for (const category of SettingsCategories) {
            this.sectionSanityCheck(category);
        }
    },
    sectionSanityCheck(section) {
        const sectionSettings = getUserSettings();

        if (sectionSettings && sectionSettings['settings'] && sectionSettings['settings'][section]) {
            this.settings[section] = sectionSettings['settings'][section];
        } else if (!sectionSettings && !this.settings[section]) {
            this.settings[section] = DefaultPlatformSettings[section];
        }
    },
    // loadVisibleTargets() {
    //     let visibleClasses = [];
    //     for (const [key, value] of Object.entries(this.settings[SECTION_VISIBLE_LAYERS])) {
    //         if (key.includes('target') && value.state) {
    //             visibleClasses.push(value.value);
    //         }
    //     }
    //     return visibleClasses;
    // },
    settings: {},
}

export default (PlatformSettings);
