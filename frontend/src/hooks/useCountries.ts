import {useState, useEffect, useCallback} from 'react';
import {
    getAllCountries, getCountryById, createCountry, updateCountry, deleteCountry,
} from '../api/repositories/countryRepository.ts';
import type {Country, CountryCreateDto, CountryUpdateDto} from '../api/types/country.ts';

export const useCountries = () => {
    const [countries, setCountries] = useState<Country[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchAll = useCallback(() => {
        setLoading(true);
        setError(null);
        getAllCountries()
            .then(res => setCountries(res.data))
            .catch(() => setError('Failed to fetch countries'))
            .finally(() => setLoading(false));
    }, []);

    useEffect(() => {
        fetchAll();
    }, [fetchAll]);

    const addCountry = async (data: CountryCreateDto) => {
        await createCountry(data);
        fetchAll();
    };

    const editCountry = async (id: number, data: CountryUpdateDto) => {
        await updateCountry(id, data);
        fetchAll();
    };

    const removeCountry = async (id: number) => {
        await deleteCountry(id);
        fetchAll();
    };

    return {
        countries,
        loading,
        error,
        refetch: fetchAll,
        addCountry,
        editCountry,
        removeCountry
    };
};

export const useCountry = (id: number) => {
    const [country, setCountry] = useState<Country | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getCountryById(id)
            .then(res => setCountry(res.data))
            .catch(() => setError('Failed to fetch country'))
            .finally(() => setLoading(false));
    }, [id]);

    return {country, loading, error};
};