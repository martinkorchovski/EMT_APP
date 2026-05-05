import { useState, useEffect } from 'react';
import { getAllCountries, getCountryById } from '../api/repositories/countryRepository.ts';
import type {Country} from "../api/types/country.ts";

export const useCountries = () => {
    const [countries, setCountries] = useState<Country[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getAllCountries()
            .then(res => setCountries(res.data))
            .catch(() => setError('Failed to fetch countries'))
            .finally(() => setLoading(false));
    }, []);

    return { countries, loading, error };
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

    return { country, loading, error };
};