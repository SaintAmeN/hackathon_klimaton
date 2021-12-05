import * as actionTypes from './actionTypes';
import axios from 'axios';
import { EUREKA_URL_HOST} from '../../assets/constants/constants';

export const fetchApplicationsStart = () => {
    return {
        type: actionTypes.FETCH_APPLICATIONS_START
    }
}

export const fetchApplications = () => {
    return dispatch => {
        dispatch(fetchApplicationsStart());
        axios.get('http://' + EUREKA_URL_HOST + ':8761/status')
            .then(response => dispatch(fetchApplicationsSuccess(response.data)))
            .catch(error => dispatch(fetchApplicationsFail(error)));
    }
}

export const fetchApplicationsFail = error => {
    return {
        type: actionTypes.FETCH_APPLICATIONS_FAIL,
        error: error
    }
}

export const fetchApplicationsSuccess = registeredApplications => {
    return {
        type: actionTypes.FETCH_APPLICATIONS_SUCCESS,
        registeredApplications: registeredApplications
    }
}
