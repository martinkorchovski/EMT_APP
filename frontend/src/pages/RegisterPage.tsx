import { useState } from 'react';
import { Container, TextField, Button, Typography, Alert, Box } from '@mui/material';
import { useNavigate, Link } from 'react-router-dom';
import { register } from '../api/repositories/userRepository.ts';

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [repeatPassword, setRepeatPassword] = useState('');
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleRegister = async () => {
        if (password !== repeatPassword) {
            setError('Passwords do not match');
            return;
        }
        try {
            const res = await register({ username, password, repeatPassword });
            localStorage.setItem('token', res.data.token);
            navigate('/');
        } catch {
            setError('Registration failed. Username may already exist.');
        }
    };

    return (
        <Container maxWidth="sm">
            <Box sx={{ mt: 8, display: 'flex', flexDirection: 'column', gap: 2 }}>
                <Typography variant="h4" sx={{ textAlign: 'center' }}>Register</Typography>
                {error && <Alert severity="error">{error}</Alert>}
                <TextField
                    label="Username"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                />
                <TextField
                    label="Password"
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />
                <TextField
                    label="Repeat Password"
                    type="password"
                    value={repeatPassword}
                    onChange={e => setRepeatPassword(e.target.value)}
                />
                <Button variant="contained" onClick={handleRegister}>Register</Button>
                <Box sx={{ textAlign: 'center' }}>
                    <Typography>Already have an account? <Link to="/login">Login</Link></Typography>
                </Box>
            </Box>
        </Container>
    );
};

export default RegisterPage;