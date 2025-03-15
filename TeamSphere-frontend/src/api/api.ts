import axios from 'axios';

const api = axios.create({
    baseURL: 'http://192.168.100.60:8080',
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, (error: Error) => {
    return Promise.reject(error);
});

export default api;