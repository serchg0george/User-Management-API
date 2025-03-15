import {useState} from 'react';
import axios from 'axios';
import {useForm, SubmitHandler} from 'react-hook-form';
import {useNavigate} from 'react-router-dom';
import {AuthFormData} from "../models/authFormData.ts";
import {AuthResponse} from "../models/authResponseData.ts";
import {baseUrl} from "@/config/BaseUrl.ts";

const AuthForm = () => {
    const [isRegister, setIsRegister] = useState<boolean>(true);
    const {register, handleSubmit, formState: {errors}} = useForm<AuthFormData>({
        defaultValues: {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
        },
    });
    const navigate = useNavigate();

    const onSubmit: SubmitHandler<AuthFormData> = async (data: AuthFormData) => {

        const url = isRegister
            ? baseUrl + '/api/v1/auth/register'
            : baseUrl + '/api/v1/auth/login';

        try {
            const response = await axios.post<AuthResponse>(url, data);
            console.log('Response:', response.data);
            localStorage.setItem('token', response.data.token);
            navigate('/main');
        } catch (error) {
            if (axios.isAxiosError(error)) {
                console.error('Error:', error.response?.data || error.message);
            } else {
                console.error('Unexpected error:', error);
            }
        }
    };

    return (
        <div>
            <h1>{isRegister ? 'Register' : 'Login'}</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
                {isRegister && (
                    <>
                        <input
                            {...register('firstName', {required: 'First name is required'})}
                            placeholder="First Name"
                        />
                        {errors.firstName && <p>{errors.firstName.message}</p>}
                        <input
                            {...register('lastName', {required: 'Last name is required'})}
                            placeholder="Last Name"
                        />
                        {errors.lastName && <p>{errors.lastName.message}</p>}
                    </>
                )}
                <input
                    {...register('email', {
                        required: 'Email is required',
                        pattern: {
                            value: /^\S+@\S+$/i,
                            message: 'Invalid email address'
                        }
                    })}
                    placeholder="Email"
                />
                {errors.email && <p>{errors.email.message}</p>}
                <input
                    {...register('password', {required: 'Password is required'})}
                    type="password"
                    placeholder="Password"
                />
                {errors.password && <p>{errors.password.message}</p>}
                <button type="submit">{isRegister ? 'Register' : 'Login'}</button>
            </form>
            <button onClick={() => setIsRegister(!isRegister)}>
                {isRegister ? 'Already have an account? Login' : 'Need an account? Register'}
            </button>
        </div>
    );
};

export default AuthForm;