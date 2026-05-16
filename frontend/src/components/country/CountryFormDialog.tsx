import {
    Dialog, DialogTitle, DialogContent, DialogActions,
    Button, TextField, Stack
} from '@mui/material';
import { useState, useEffect } from 'react';
import type { Country, CountryCreateDto, CountryUpdateDto } from '../../api/types/country.ts';

interface CountryFormDialogProps {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: CountryCreateDto | CountryUpdateDto) => void;
    existing?: Country | null;
}

const CountryFormDialog = ({ open, onClose, onSubmit, existing }: CountryFormDialogProps) => {
    const [name, setName] = useState('');
    const [continent, setContinent] = useState('');

    useEffect(() => {
        if (existing) {
            setName(existing.name);
            setContinent(existing.continent);
        } else {
            setName('');
            setContinent('');
        }
    }, [existing, open]);

    const handleSubmit = () => {
        onSubmit({ name, continent });
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>{existing ? 'Edit Country' : 'Add Country'}</DialogTitle>
            <DialogContent>
                <Stack spacing={2} sx={{ mt: 1 }}>
                    <TextField
                        label="Name"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        fullWidth
                    />
                    <TextField
                        label="Continent"
                        value={continent}
                        onChange={e => setContinent(e.target.value)}
                        fullWidth
                    />
                </Stack>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button variant="contained" onClick={handleSubmit}>
                    {existing ? 'Save' : 'Add'}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default CountryFormDialog;