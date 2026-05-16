import { useState, useEffect, useCallback } from 'react';
import {
    getAllAccommodations,
    getAccommodationById,
    createAccommodation,
    updateAccommodation,
    deleteAccommodation,
} from '../api/repositories/accommodationRepository.ts';
import type {
    Accommodation,
    AccommodationDetail,
    AccommodationCreateDto,
    AccommodationUpdateDto
} from '../api/types/accommodation.ts';

export const useAccommodations = () => {
    const [accommodations, setAccommodations] = useState<Accommodation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchAll = useCallback(() => {
        setLoading(true);
        setError(null);
        getAllAccommodations()
            .then(res => setAccommodations(res.data.content))
            .catch(() => setError('Failed to fetch accommodations'))
            .finally(() => setLoading(false));
    }, []);

    useEffect(() => {
        fetchAll();
    }, [fetchAll]);

    const addAccommodation = async (data: AccommodationCreateDto) => {
        await createAccommodation(data);
        fetchAll();
    };

    const editAccommodation = async (id: number, data: AccommodationUpdateDto) => {
        await updateAccommodation(id, data);
        fetchAll();
    };

    const removeAccommodation = async (id: number) => {
        await deleteAccommodation(id);
        fetchAll();
    };

    return {
        accommodations,
        loading,
        error,
        refetch: fetchAll,
        addAccommodation,
        editAccommodation,
        removeAccommodation
    };
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