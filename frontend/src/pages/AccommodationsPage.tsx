import { Container, Typography, CircularProgress, Alert, Table, TableBody, TableCell, TableHead, TableRow, Paper } from '@mui/material';
import { useAccommodations } from '../hooks/useAccommodations';

const AccommodationsPage = () => {
    const { accommodations, loading, error } = useAccommodations();

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            <Typography variant="h4" gutterBottom sx={{ textAlign: 'center' }}>Accommodations</Typography>
            <Paper>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Rooms</TableCell>
                            <TableCell>Host</TableCell>
                            <TableCell>Category</TableCell>
                            <TableCell>Rented</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {accommodations.map(acc => (
                            <TableRow key={acc.id}>
                                <TableCell>{acc.name}</TableCell>
                                <TableCell>{acc.numRooms}</TableCell>
                                <TableCell>{acc.host}</TableCell>
                                <TableCell>{acc.category}</TableCell>
                                <TableCell>{acc.isRented ? 'Yes' : 'No'}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    );
};

export default AccommodationsPage;