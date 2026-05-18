import { useState, useEffect, useCallback } from 'react';
import { getAllReservations, createReservation } from '../api/repositories/reservationRepository.ts';
import type { Reservation, ReservationCreateDto } from '../api/types/reservation.ts';

export const useReservations = () => {
    const [reservations, setReservations] = useState<Reservation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchAll = useCallback(() => {
        setLoading(true);
        setError(null);
        getAllReservations()
            .then(res => setReservations(res.data))
            .catch(() => setError('Failed to fetch reservations'))
            .finally(() => setLoading(false));
    }, []);

    useEffect(() => {
        fetchAll();
    }, [fetchAll]);

    const addReservation = async (data: ReservationCreateDto) => {
        await createReservation(data);
        fetchAll();
    };

    return { reservations, loading, error, addReservation };
};