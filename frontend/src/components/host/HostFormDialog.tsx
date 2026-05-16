import {
    Dialog, DialogTitle, DialogContent, DialogActions,
    Button, TextField, Stack, MenuItem
} from '@mui/material';
import { useState, useEffect } from 'react';
import type { Host, HostCreateDto, HostUpdateDto } from '../../api/types/host.ts';
import { useCountries } from '../../hooks/useCountries.ts';

interface HostFormDialogProps {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: HostCreateDto | HostUpdateDto) => void;
    existing?: Host | null;
}

const HostFormDialog = ({ open, onClose, onSubmit, existing }: HostFormDialogProps) => {
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [countryId, setCountryId] = useState('');

    const { countries } = useCountries();

    useEffect(() => {
        if (existing) {
            setName(existing.name);
            setSurname(existing.surname);
            const matchedCountry = countries.find(c => c.name === existing.countryName);
            setCountryId(matchedCountry ? String(matchedCountry.id) : '');
        } else {
            setName('');
            setSurname('');
            setCountryId('');
        }
    }, [existing, open, countries]);

    const handleSubmit = () => {
        onSubmit({
            name,
            surname,
            countryId: Number(countryId),
        });
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>{existing ? 'Edit Host' : 'Add Host'}</DialogTitle>
            <DialogContent>
                <Stack spacing={2} sx={{ mt: 1 }}>
                    <TextField
                        label="Name"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        fullWidth
                    />
                    <TextField
                        label="Surname"
                        value={surname}
                        onChange={e => setSurname(e.target.value)}
                        fullWidth
                    />
                    <TextField
                        select
                        label="Country"
                        value={countryId}
                        onChange={e => setCountryId(e.target.value)}
                        fullWidth
                    >
                        {countries.map(c => (
                            <MenuItem key={c.id} value={c.id}>{c.name}</MenuItem>
                        ))}
                    </TextField>
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

export default HostFormDialog;