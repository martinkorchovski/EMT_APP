import axiosInstance from './axiosInstance.ts';
import type {User} from "../types/user.ts";

export const getAllUsers = () =>
    axiosInstance.get<User[]>('/users');

export const getUserById = (id: number) =>
    axiosInstance.get<User>(`/users/${id}`);