import { useState } from 'react';
import { Typography, Button, Box, Alert } from '@mui/material';
import ReservationGrid from '../components/reservation/ReservationGrid.tsx';
import ReservationFormDialog from '../components/reservation/ReservationFormDialog.tsx';
import type { ReservationCreateDto } from '../api/types/reservation.ts';
import {useReservations} from "../hooks/userReservations.ts";

const ReservationsPage = () => {
    const { reservations, loading, error, addReservation } = useReservations();
    const [dialogOpen, setDialogOpen] = useState(false);
    const [submitError, setSubmitError] = useState<string | null>(null);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleSubmit = async (data: ReservationCreateDto) => {
        try {
            setSubmitError(null);
            await addReservation(data);
        } catch (err: any) {
            setSubmitError(err.response?.data || 'Host can not reserve its own accommodation!');
        }
    };

    return (
        <div>
            <Box sx={{ position: 'relative', display: 'flex', justifyContent: 'center', alignItems: 'center', mb: 3 }}>
                <Typography variant="h4">Reservations</Typography>
                <Button variant="outlined" size="large" onClick={() => setDialogOpen(true)} sx={{ position: 'absolute', right: 0 }}>
                    Add Reservation
                </Button>
            </Box>

            {submitError && (
                <Alert severity="error" sx={{ mb: 2 }} onClose={() => setSubmitError(null)}>
                    {submitError}
                </Alert>
            )}

            <ReservationGrid reservations={reservations} />

            <ReservationFormDialog
                open={dialogOpen}
                onClose={() => setDialogOpen(false)}
                onSubmit={handleSubmit}
            />
        </div>
    );
};

export default ReservationsPage;