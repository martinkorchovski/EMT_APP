import axiosInstance from './axiosInstance.ts';
import type { User, LoginDto, RegisterDto, AuthResponse } from '../types/user.ts';

export const getAllUsers = () =>
    axiosInstance.get<User[]>('/users');

export const getUserById = (id: number) =>
    axiosInstance.get<User>(`/users/${id}`);

export const login = (data: LoginDto) =>
    axiosInstance.post<AuthResponse>('/auth/login', data);

export const register = (data: RegisterDto) =>
    axiosInstance.post<AuthResponse>('/auth/register', data);