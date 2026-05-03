import { useParams } from 'react-router-dom';
import { Container, Typography, CircularProgress, Alert, Paper, Box } from '@mui/material';
import { useHost } from '../hooks/useHosts';

const HostDetailsPage = () => {
    const { id } = useParams();
    const { host, loading, error } = useHost(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!host) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3, mt: 3 }}>
                <Typography variant="h4">{host.name} {host.surname}</Typography>
                <Box sx={{ mt: 2 }}>
                    <Typography>Country: {host.countryName}</Typography>
                </Box>
            </Paper>
        </Container>
    );
};
export default HostDetailsPage;