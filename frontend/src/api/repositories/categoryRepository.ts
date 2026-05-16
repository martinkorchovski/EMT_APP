import axiosInstance from './axiosInstance.ts';
import type { Category } from '../types/category.ts';

export const getAllCategories = () =>
    axiosInstance.get<Category[]>('/categories');