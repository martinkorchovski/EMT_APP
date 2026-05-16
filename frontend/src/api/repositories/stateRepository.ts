import axiosInstance from './axiosInstance.ts';
import type { State } from '../types/state.ts';

export const getAllStates = () =>
    axiosInstance.get<State[]>('/states');