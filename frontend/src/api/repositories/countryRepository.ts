import axiosInstance from './axiosInstance.ts';
import type { Country } from '../../types';

export const getAllCountries = () =>
    axiosInstance.get<Country[]>('/countries');

export const getCountryById = (id: number) =>
    axiosInstance.get<Country>(`/countries/${id}`);