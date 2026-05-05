import {Button, Card, CardContent, Typography} from '@mui/material';
import type { Accommodation } from '../../api/types/accommodation.ts';
import {useNavigate} from "react-router-dom";

interface AccommodationCardProps {
    accommodation: Accommodation;
}

const AccommodationCard = ({ accommodation }: AccommodationCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant='h5'>{accommodation.name}</Typography>
                <Typography variant='subtitle1'>{accommodation.host}</Typography>
                <Button
                    variant="contained"
                    size="small"
                    sx={{ mt: 1, alignSelf: 'flex-end' }}
                    onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                >Details</Button>
            </CardContent>
        </Card>
    );
};

export default AccommodationCard;