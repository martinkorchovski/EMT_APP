import { useState } from 'react';
import { Typography, Button, Box } from '@mui/material';
import CountryGrid from '../components/country/CountryGrid.tsx';
import CountryFormDialog from '../components/country/CountryFormDialog.tsx';
import { useCountries } from '../hooks/useCountries.ts';
import type { Country, CountryCreateDto, CountryUpdateDto } from '../api/types/country.ts';
import { isAdmin } from '../utils/auth.ts';

const CountriesPage = () => {
    const { countries, loading, error, addCountry, editCountry, removeCountry } = useCountries();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [selected, setSelected] = useState<Country | null>(null);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleAdd = () => {
        setSelected(null);
        setDialogOpen(true);
    };

    const handleEdit = (country: Country) => {
        setSelected(country);
        setDialogOpen(true);
    };

    const handleDelete = async (id: number) => {
        await removeCountry(id);
    };

    const handleSubmit = async (data: CountryCreateDto | CountryUpdateDto) => {
        if (selected) {
            await editCountry(selected.id, data as CountryUpdateDto);
        } else {
            await addCountry(data as CountryCreateDto);
        }
    };

    return (
        <div>
            <Box sx={{ position: 'relative', display: 'flex', justifyContent: 'center', alignItems: 'center', mb: 3 }}>
                <Typography variant="h4">Countries</Typography>
                {isAdmin() && (
                    <Button variant="outlined" size="large" onClick={handleAdd} sx={{ position: 'absolute', right: 0 }}>
                    Add Country
                </Button>)}
            </Box>

            <CountryGrid
                countries={countries}
                onEdit={handleEdit}
                onDelete={handleDelete}
                isAdmin={isAdmin()}
            />

            <CountryFormDialog
                open={dialogOpen}
                onClose={() => setDialogOpen(false)}
                onSubmit={handleSubmit}
                existing={selected}
            />
        </div>
    );
};

export default CountriesPage;