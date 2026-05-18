import {useState, useEffect} from 'react';
import {getAllCategories} from '../api/repositories/categoryRepository.ts';
import type {Category} from '../api/types/category.ts';

export const useCategories = () => {
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        getAllCategories().then(res => setCategories(res.data));
    }, []);

    return {categories};
};