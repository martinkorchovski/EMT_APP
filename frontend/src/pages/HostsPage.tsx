import { Container, Typography, CircularProgress, Alert, Table, TableBody, TableCell, TableHead, TableRow, Paper } from '@mui/material';
import { useHosts } from '../hooks/useHosts';

const HostsPage = () => {
    const { hosts, loading, error } = useHosts();

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            <Typography variant="h4" gutterBottom sx={{ textAlign: 'center' }}>Hosts</Typography>
            <Paper>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Surname</TableCell>
                            <TableCell>Country</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {hosts.map(host => (
                            <TableRow key={host.id}>
                                <TableCell>{host.name}</TableCell>
                                <TableCell>{host.surname}</TableCell>
                                <TableCell>{host.countryName}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    );
};

export default HostsPage;