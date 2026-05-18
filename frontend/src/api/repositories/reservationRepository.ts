import axiosInstance from './axiosInstance.ts';
import type { Reservation, ReservationCreateDto } from '../types/reservation.ts';

export const getAllReservations = () =>
    axiosInstance.get<Reservation[]>('/reservations');

export const createReservation = (data: ReservationCreateDto) =>
    axiosInstance.post<Reservation>('/reservations/reserve', data);