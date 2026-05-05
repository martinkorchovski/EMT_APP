import axiosInstance from './axiosInstance.ts';
import type { Host } from '../../types';

export const getAllHosts = () =>
    axiosInstance.get<Host[]>('/hosts');

export const getHostById = (id: number) =>
    axiosInstance.get<Host>(`/hosts/${id}`);