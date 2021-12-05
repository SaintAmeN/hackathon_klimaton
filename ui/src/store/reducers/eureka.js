import {updateObject} from '../../shared/utility';
import * as actionTypes from '../actions/actionTypes';

const initialState = {
    registeredApplications: [],
    loading: false,
    error: null
}

const fetchApplicationsStart = state => {
    return updateObject(state, {loading: true, error: null});
}

const fetchApplicationsFail = (state, error) => {
    return updateObject(state, {loading: false, error: error});
}

const fetchApplicationsSuccess = (state, registeredApplications) => {
    return updateObject(state, {registeredApplications: registeredApplications, loading: false, error: null})
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.FETCH_APPLICATIONS_START:
            return fetchApplicationsStart(state);
        case actionTypes.FETCH_APPLICATIONS_FAIL:
            return fetchApplicationsFail(state, action.error);
        case actionTypes.FETCH_APPLICATIONS_SUCCESS:
            return fetchApplicationsSuccess(state, action.registeredApplications);
        default:
            return state;
    }
}

export default reducer;
