import {Button, Card, CardContent, Typography} from '@mui/material';
import type { Country } from '../../api/types/country.ts';
import {useNavigate} from "react-router-dom";

interface CountryCardProps {
    country: Country;
}

const CountryCard = ({ country }: CountryCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant='h5'>{country.name}</Typography>
                <Button
                    variant="contained"
                    size="small"
                    sx={{ mt: 1, alignSelf: 'flex-end' }}
                    onClick={() => navigate(`/countries/${country.id}`)}
                >Details</Button>
            </CardContent>
        </Card>
    );
};

export default CountryCard;