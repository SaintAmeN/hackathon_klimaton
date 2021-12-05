import axios from 'axios';
import {BASE_URL} from '../assets/constants/constants';

const instance = axios.create({
    baseURL: BASE_URL
});

instance.interceptors.request.use(req => {
    const token = localStorage.getItem('token');
    if (token) {
        req.headers = {'Authorization': 'Bearer ' + token};
    }
    return req;
});

export default instance;
