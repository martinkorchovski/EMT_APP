import axiosInstance from './axiosInstance.ts';
import type { Host, HostCreateDto, HostUpdateDto } from '../types/host.ts';

export const getAllHosts = () =>
    axiosInstance.get<Host[]>('/hosts');

export const getHostById = (id: number) =>
    axiosInstance.get<Host>(`/hosts/${id}`);

export const createHost = (data: HostCreateDto) =>
    axiosInstance.post<Host>('/hosts/addHost', data);

export const updateHost = (id: number, data: HostUpdateDto) =>
    axiosInstance.put<Host>(`/hosts/edit/${id}`, data);

export const deleteHost = (id: number) =>
    axiosInstance.delete(`/hosts/deleteHost/${id}`);