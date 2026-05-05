import { useState } from 'react';
import { Container, TextField, Button, Typography, Alert, Box } from '@mui/material';
import axiosInstance from '../api/repositories/axiosInstance.ts';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const res = await axiosInstance.post('/auth/login', { username, password });
            localStorage.setItem('token', res.data.token);
            navigate('/');
        } catch {
            setError('Invalid username or password');
        }
    };

    return (
        <Container maxWidth="sm">
            <Box sx={{ mt: 8, display: 'flex', flexDirection: 'column', gap: 2 }}>
                <Typography variant="h4" sx={{ textAlign: 'center' }}>Login</Typography>
                {error && <Alert severity="error">{error}</Alert>}
                <TextField label="Username" value={username} onChange={e => setUsername(e.target.value)} />
                <TextField label="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
                <Button variant="contained" onClick={handleLogin}>Login</Button>
            </Box>
        </Container>
    );
};

export default LoginPage;