import { useParams } from 'react-router-dom';
import {Container, Typography, CircularProgress, Alert, Paper, Box} from '@mui/material';
import {useUser} from "../hooks/useUsers.ts";

const UserDetailsPage = () => {
    const { id } = useParams();
    const { user, loading, error } = useUser(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!user) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3, mt: 3 }}>
                <Typography variant="h4">{user.username}</Typography>
                <Box sx={{ mt: 2 }}>
                    <Typography>ID: {user.id}</Typography>
                    <Typography>{user.role}</Typography>
                </Box>
            </Paper>
        </Container>
    );
};
export default UserDetailsPage;