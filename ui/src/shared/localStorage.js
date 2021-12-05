
export const getUserSettings = () => {
    return JSON.parse(localStorage.getItem('userSettings'));
}

export const updateUserSettings = settings => {
    localStorage.setItem('userSettings', JSON.stringify(settings));
}

export const getPanelSettings = () => {
    return JSON.parse(localStorage.getItem('panelSettings'));
}

export const updatePanelSettings = settings => {
    localStorage.setItem('panelSettings', JSON.stringify(settings));
}
