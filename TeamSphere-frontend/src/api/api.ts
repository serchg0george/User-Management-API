import axios from 'axios';
import {baseUrl} from "@/config/BaseUrl.ts";

const api = axios.create({
    baseURL: baseUrl,
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