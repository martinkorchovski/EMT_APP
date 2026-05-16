import axiosInstance from './axiosInstance.ts';
import type { Country, CountryCreateDto, CountryUpdateDto } from '../types/country.ts';

export const getAllCountries = () =>
    axiosInstance.get<Country[]>('/countries');

export const getCountryById = (id: number) =>
    axiosInstance.get<Country>(`/countries/${id}`);

export const createCountry = (data: CountryCreateDto) =>
    axiosInstance.post<Country>('/countries/add', data);

export const updateCountry = (id: number, data: CountryUpdateDto) =>
    axiosInstance.put<Country>(`/countries/edit/${id}`, data);

export const deleteCountry = (id: number) =>
    axiosInstance.delete(`/countries/delete/${id}`);