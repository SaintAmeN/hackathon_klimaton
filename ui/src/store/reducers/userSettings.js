import * as actionTypes from '../actions/actionTypes';
import {updateObject} from '../../shared/utility';

const initialState = {
    content: [],
    totalElements: null,
    loading: true,
    error: null,
    page: 0,
    perPage: 10
}

const fetchUsersPageStart = state => {
    return updateObject(state, {
        content: [],
        totalElements: null,
        loading: true,
        error: null
    });
}

const fetchUsersPageFail = (state, action) => {
    return updateObject(state, {
        loading: false,
        error: action.error
    });
}

const fetchUsersPageSuccess = (state, action) => {
    return updateObject(state, {
        content: action.users,
        totalElements: action.totalUsers,
        loading: false,
        page: action.page,
        perPage: action.perPage
    });
}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.FETCH_USERS_PAGE_START:
            return fetchUsersPageStart(state);
        case actionTypes.FETCH_USERS_PAGE_SUCCESS:
            return fetchUsersPageSuccess(state, action);
        case actionTypes.FETCH_USERS_PAGE_FAIL:
            return fetchUsersPageFail(state, action);
        default:
            return state;
    }
}

export default reducer;
