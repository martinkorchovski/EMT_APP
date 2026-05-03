import { Container, Typography, CircularProgress, Alert, Table, TableBody, TableCell, TableHead, TableRow, Paper } from '@mui/material';
import { useCountries } from '../hooks/useCountries';

const CountriesPage = () => {
    const { countries, loading, error } = useCountries();

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            <Typography variant="h4" gutterBottom sx={{ textAlign: 'center' }}>Countries</Typography>
            <Paper>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Continent</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {countries.map(country => (
                            <TableRow key={country.id}>
                                <TableCell>{country.name}</TableCell>
                                <TableCell>{country.continent}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    );
};

export default CountriesPage;