import { useState, useEffect } from 'react';
import { getAllStates } from '../api/repositories/stateRepository.ts';
import type { State } from '../api/types/state.ts';

export const useStates = () => {
    const [states, setStates] = useState<State[]>([]);

    useEffect(() => {
        getAllStates().then(res => setStates(res.data));
    }, []);

    return { states };
};