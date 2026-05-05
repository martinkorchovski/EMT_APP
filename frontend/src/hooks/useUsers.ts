import {useEffect, useState} from "react";
import type {User} from "../api/types/user.ts";
import { getAllUsers, getUserById } from '../api/repositories/userRepository';

export const useUsers = () => {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getAllUsers()
            .then(res => setUsers(res.data))
            .catch(() => setError('Failed to fetch users'))
            .finally(() => setLoading(false));
    }, []);

    return { users: users, loading, error };
};

export const useUser = (id: number) => {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        getUserById(id)
            .then(res => setUser(res.data))
            .catch(() => setError('Failed to fetch user'))
            .finally(() => setLoading(false));
    }, [id]);

    return { user, loading, error };
};