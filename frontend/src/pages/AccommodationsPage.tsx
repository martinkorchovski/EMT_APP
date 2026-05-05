import AccommodationGrid from '../components/accommodation/AccommodationGrid.tsx';
import { useAccommodations } from '../hooks/useAccommodations.ts';
import {Typography} from "@mui/material";

const AccommodationsPage = () => {
    const { accommodations, loading, error } = useAccommodations();

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <Typography variant='h4' sx={{ textAlign: 'center', mb: 2 }}>Accommodations</Typography>
            <AccommodationGrid accommodations={accommodations} />
        </div>
    );
};

export default AccommodationsPage;