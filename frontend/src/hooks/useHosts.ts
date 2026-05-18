import {useState, useEffect, useCallback} from 'react';
import {
    getAllHosts, getHostById, createHost, updateHost, deleteHost,
} from '../api/repositories/hostRepository.ts';
import type {Host, HostCreateDto, HostUpdateDto} from '../api/types/host.ts';

export const useHosts = () => {
    const [hosts, setHosts] = useState<Host[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchAll = useCallback(() => {
        setLoading(true);
        setError(null);
        getAllHosts()
            .then(res => setHosts(res.data))
            .catch(() => setError('Failed to fetch hosts'))
            .finally(() => setLoading(false));
    }, []);

    useEffect(() => {
        fetchAll();
    }, [fetchAll]);

    const addHost = async (data: HostCreateDto) => {
        await createHost(data);
        fetchAll();
    };

    const editHost = async (id: number, data: HostUpdateDto) => {
        await updateHost(id, data);
        fetchAll();
    };

    const removeHost = async (id: number) => {
        await deleteHost(id);
        fetchAll();
    };

    return {
        hosts,
        loading,
        error,
        refetch: fetchAll,
        addHost,
        editHost,
        removeHost
    };
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

    return {host, loading, error};
};