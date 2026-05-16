import {
    Dialog, DialogTitle, DialogContent, DialogActions,
    Button, TextField, Stack, MenuItem
} from '@mui/material';
import { useState, useEffect } from 'react';
import type { Accommodation, AccommodationCreateDto, AccommodationUpdateDto } from '../../api/types/accommodation.ts';
import { useCategories } from '../../hooks/useCategories.ts';
import { useStates } from '../../hooks/useStates.ts';
import { useHosts } from '../../hooks/useHosts.ts';

interface AccommodationFormDialogProps {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: AccommodationCreateDto | AccommodationUpdateDto) => void;
    existing?: Accommodation | null;
}

const AccommodationFormDialog = ({ open, onClose, onSubmit, existing }: AccommodationFormDialogProps) => {
    const [name, setName] = useState('');
    const [numRooms, setNumRooms] = useState('');
    const [categoryId, setCategoryId] = useState('');
    const [stateId, setStateId] = useState('');
    const [hostId, setHostId] = useState('');

    const { categories } = useCategories();
    const { states } = useStates();
    const { hosts } = useHosts();

    useEffect(() => {
        if (existing) {
            setName(existing.name);
            setNumRooms(String(existing.numRooms));

            const matchedCategory = categories.find(c => c.name === existing.category);
            const matchedState = states.find(s => s.name === existing.state);
            const matchedHost = hosts.find(h => `${h.name} ${h.surname}` === existing.host);

            setCategoryId(matchedCategory ? String(matchedCategory.id) : '');
            setStateId(matchedState ? String(matchedState.id) : '');
            setHostId(matchedHost ? String(matchedHost.id) : '');
        } else {
            setName('');
            setNumRooms('');
            setCategoryId('');
            setStateId('');
            setHostId('');
        }
    }, [existing, open, categories, states, hosts]);

    const handleSubmit = () => {
        onSubmit({
            name,
            numRooms: Number(numRooms),
            categoryId: Number(categoryId),
            stateId: Number(stateId),
            hostId: Number(hostId),
        });
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>{existing ? 'Edit Accommodation' : 'Add Accommodation'}</DialogTitle>
            <DialogContent>
                <Stack spacing={2} sx={{ mt: 1 }}>
                    <TextField
                        label="Name"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        fullWidth
                    />
                    <TextField
                        label="Number of Rooms"
                        type="number"
                        value={numRooms}
                        onChange={e => setNumRooms(e.target.value)}
                        fullWidth
                    />
                    <TextField
                        select
                        label="Category"
                        value={categoryId}
                        onChange={e => setCategoryId(e.target.value)}
                        fullWidth
                    >
                        {categories.map(c => (
                            <MenuItem key={c.id} value={c.id}>{c.name}</MenuItem>
                        ))}
                    </TextField>
                    <TextField
                        select
                        label="State"
                        value={stateId}
                        onChange={e => setStateId(e.target.value)}
                        fullWidth
                    >
                        {states.map(s => (
                            <MenuItem key={s.id} value={s.id}>{s.name}</MenuItem>
                        ))}
                    </TextField>
                    <TextField
                        select
                        label="Host"
                        value={hostId}
                        onChange={e => setHostId(e.target.value)}
                        fullWidth
                    >
                        {hosts.map(h => (
                            <MenuItem key={h.id} value={h.id}>{h.name} {h.surname}</MenuItem>
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

export default AccommodationFormDialog;