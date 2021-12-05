import * as actionTypes from './actionTypes';
import instance from '../../axios/axios';

const fetchUsersPageStart = () => {
    return {
        type: actionTypes.FETCH_USERS_PAGE_START
    }
};

export const fetchUsersPage = (page, perPage) => {
    return dispatch => {
        dispatch(fetchUsersPageStart());
        instance.get('/user/page', {
            params: {
                page: page,
                perPage: perPage
            }
        }).then(response => {
            dispatch(fetchUsersPageSuccess(response.data.content, response.data.pageNumber, response.data.pageSize, response.data.totalElements))
        }).catch(error => {
            dispatch(fetchUsersPageFail(error));
        })
    }
};

const fetchUsersPageFail = error => {
    return {
        type: actionTypes.FETCH_USERS_PAGE_FAIL,
        error: error.message
    }
};

const fetchUsersPageSuccess = (users, page, perPage, totalUsers) => {
    return {
        type: actionTypes.FETCH_USERS_PAGE_SUCCESS,
        users: users,
        page: page,
        perPage: perPage,
        totalUsers: totalUsers
    }
};
