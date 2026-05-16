import { useState } from 'react';
import { Typography, Button, Box } from '@mui/material';
import AccommodationGrid from '../components/accommodation/AccommodationGrid.tsx';
import AccommodationFormDialog from '../components/accommodation/AccommodationFormDialog.tsx';
import { useAccommodations } from '../hooks/useAccommodations.ts';
import type { Accommodation, AccommodationCreateDto, AccommodationUpdateDto } from '../api/types/accommodation.ts';
import { isAdmin } from '../utils/auth.ts';

const AccommodationsPage = () => {
    const { accommodations, loading, error, addAccommodation, editAccommodation, removeAccommodation } = useAccommodations();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [selected, setSelected] = useState<Accommodation | null>(null);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleAdd = () => {
        setSelected(null);
        setDialogOpen(true);
    };

    const handleEdit = (accommodation: Accommodation) => {
        setSelected(accommodation);
        setDialogOpen(true);
    };

    const handleDelete = (id: number) => {
        removeAccommodation(id);
    };

    const handleSubmit = (data: AccommodationCreateDto | AccommodationUpdateDto) => {
        if (selected) {
            editAccommodation(selected.id, data as AccommodationUpdateDto);
        } else {
            addAccommodation(data as AccommodationCreateDto);
        }
    };

    return (
        <div>
            <Box sx={{ position: 'relative', display: 'flex', justifyContent: 'center', alignItems: 'center', mb: 3 }}>
                <Typography variant="h4">Accommodations</Typography>
                {isAdmin() && (
                    <Button variant="outlined" size="large" onClick={handleAdd} sx={{ position: 'absolute', right: 0 }}>
                        Add Accommodation
                    </Button>
                )}
            </Box>

            <AccommodationGrid
                accommodations={accommodations}
                onEdit={handleEdit}
                onDelete={handleDelete}
                isAdmin={isAdmin()}
            />

            <AccommodationFormDialog
                open={dialogOpen}
                onClose={() => setDialogOpen(false)}
                onSubmit={handleSubmit}
                existing={selected}
            />
        </div>
    );
};

export default AccommodationsPage;