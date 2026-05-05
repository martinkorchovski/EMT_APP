import { useState, useEffect } from 'react';
import { getAllHosts, getHostById } from '../api/repositories/hostRepository.ts';
import type {Host} from "../api/types/host.ts";

export const useHosts = () => {
    const [hosts, setHosts] = useState<Host[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getAllHosts()
            .then(res => setHosts(res.data))
            .catch(() => setError('Failed to fetch hosts'))
            .finally(() => setLoading(false));
    }, []);

    return { hosts, loading, error };
};

export const useHost = (id: number) => {
    const [host, setHost] = useState<Host | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getHostById(id)
            .then(res => setHost(res.data))
            .catch(() => setError('Failed to fetch host'))
            .finally(() => setLoading(false));
    }, [id]);

    return { host, loading, error };
};