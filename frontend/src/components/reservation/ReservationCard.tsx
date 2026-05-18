import { Card, CardContent, Typography } from '@mui/material';
import type { Reservation } from '../../api/types/reservation.ts';

interface ReservationCardProps {
    reservation: Reservation;
}

const ReservationCard = ({ reservation }: ReservationCardProps) => {
    return (
        <Card sx={{ maxWidth: 350 }}>
            <CardContent>
                <Typography variant="h6">{reservation.accommodation.name}</Typography>
                <Typography>User: {reservation.user.username}</Typography>
                <Typography>Release at: {reservation.releaseAt}</Typography>
                <Typography>Reserved at: {reservation.reservedAt}</Typography>
            </CardContent>
        </Card>
    );
};

export default ReservationCard;
