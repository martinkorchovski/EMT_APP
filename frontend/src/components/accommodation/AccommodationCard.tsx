import { Button, Card, CardContent, Typography, Stack } from '@mui/material';
import type { Accommodation } from '../../api/types/accommodation.ts';
import { useNavigate } from 'react-router-dom';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

interface AccommodationCardProps {
    accommodation: Accommodation;
    onEdit: (accommodation: Accommodation) => void;
    onDelete: (id: number) => void;
    isAdmin: boolean;
}

const AccommodationCard = ({ accommodation, onEdit, onDelete, isAdmin }: AccommodationCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant="h5">{accommodation.name}</Typography>
                <Typography variant="subtitle1">{accommodation.host}</Typography>
                <Stack direction="row" spacing={1} sx={{ mt: 1 }}>
                    <Button
                        variant="contained"
                        size="small"
                        onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                    >
                        Details
                    </Button>
                    {isAdmin && (
                        <>
                            <Button
                                variant="outlined"
                                size="small"
                                startIcon={<EditIcon />}
                                onClick={() => onEdit(accommodation)}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="contained"
                                color="error"
                                size="small"
                                startIcon={<DeleteIcon />}
                                onClick={() => onDelete(accommodation.id)}
                            >
                                Delete
                            </Button>
                        </>
                    )}
                </Stack>
            </CardContent>
        </Card>
    );
};

export default AccommodationCard;