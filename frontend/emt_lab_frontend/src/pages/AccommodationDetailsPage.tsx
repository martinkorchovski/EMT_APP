import { useParams } from 'react-router-dom';
import { Container, Typography, CircularProgress, Alert, Paper, Box } from '@mui/material';
import { useAccommodation } from '../hooks/useAccommodations';

const AccommodationDetailsPage = () => {
    const { id } = useParams();
    const { accommodation, loading, error } = useAccommodation(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!accommodation) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3, mt: 3 }}>
                <Typography variant="h4">{accommodation.name}</Typography>
                <Box sx={{ mt: 2 }}>
                    <Typography>Rooms: {accommodation.numRooms}</Typography>
                    <Typography>Category: {accommodation.category?.name}</Typography>
                    <Typography>Host: {accommodation.host?.name} {accommodation.host?.surname}</Typography>
                    <Typography>Rented: {accommodation.isRented ? 'Yes' : 'No'}</Typography>
                </Box>
            </Paper>
        </Container>
    );
};

export default AccommodationDetailsPage;