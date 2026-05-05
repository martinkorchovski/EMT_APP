import axiosInstance from './axiosInstance.ts';

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