import { useParams } from 'react-router-dom';
import { Container, Typography, CircularProgress, Alert, Paper, Box } from '@mui/material';
import { useCountry } from '../hooks/useCountries';

const CountryDetailsPage = () => {
    const { id } = useParams();
    const { country, loading, error } = useCountry(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!country) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3, mt: 3 }}>
                <Typography variant="h4">{country.name}</Typography>
                <Box sx={{ mt: 2 }}>
                    <Typography>Continent: {country.continent}</Typography>
                </Box>
            </Paper>
        </Container>
    );
};
export default CountryDetailsPage;