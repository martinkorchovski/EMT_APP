import axiosInstance from './axiosInstance.ts';
import type { AccommodationCreateDto, AccommodationUpdateDto } from '../types/accommodation.ts';

export const getAllAccommodations = () =>
    axiosInstance.get<any>('/accommodations', {
        params: {
            page: 0,
            size: 100,
            sortBy: 'name',
            direction: 'ASC'
        }
    });

export const getAccommodationById = (id: number) =>
    axiosInstance.get<any>(`/accommodations/${id}/withHostAndCountry`);

export const createAccommodation = (data: AccommodationCreateDto) =>
    axiosInstance.post<any>('/accommodations/add', data);

export const updateAccommodation = (id: number, data: AccommodationUpdateDto) =>
    axiosInstance.put<any>(`/accommodations/edit/${id}`, data);

export const deleteAccommodation = (id: number) =>
    axiosInstance.delete(`/accommodations/delete/${id}`);