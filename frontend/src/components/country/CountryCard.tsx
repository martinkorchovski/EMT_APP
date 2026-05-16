import { Button, Card, CardContent, Typography, Stack } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import type { Country } from '../../api/types/country.ts';
import { useNavigate } from 'react-router-dom';

interface CountryCardProps {
    country: Country;
    onEdit: (country: Country) => void;
    onDelete: (id: number) => void;
    isAdmin: boolean;
}

const CountryCard = ({ country, onEdit, onDelete, isAdmin }: CountryCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant="h5">{country.name}</Typography>
                <Typography variant="subtitle1">{country.continent}</Typography>
                <Stack direction="row" spacing={1} sx={{ mt: 1 }}>
                    <Button
                        variant="contained"
                        size="small"
                        onClick={() => navigate(`/countries/${country.id}`)}
                    >
                        Details
                    </Button>
                    {isAdmin && (
                        <>
                            <Button
                                variant="outlined"
                                size="small"
                                startIcon={<EditIcon />}
                                onClick={() => onEdit(country)}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="contained"
                                color="error"
                                size="small"
                                startIcon={<DeleteIcon />}
                                onClick={() => onDelete(country.id)}
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

export default CountryCard;