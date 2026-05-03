import { useState, useEffect } from 'react';
import type {Accommodation, AccommodationDetail} from '../types';
import { getAllAccommodations, getAccommodationById } from '../api/accommodationRepository';

export const useAccommodations = () => {
    const [accommodations, setAccommodations] = useState<Accommodation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getAllAccommodations()
            .then(res => setAccommodations(res.data.content))
            .catch(() => setError('Failed to fetch accommodations'))
            .finally(() => setLoading(false));
    }, []);

    return { accommodations, loading, error };
};

export const useAccommodation = (id: number) => {
    const [accommodation, setAccommodation] = useState<AccommodationDetail | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getAccommodationById(id)
            .then(res => setAccommodation(res.data))
            .catch(() => setError('Failed to fetch accommodation'))
            .finally(() => setLoading(false));
    }, [id]);

    return { accommodation, loading, error };
};