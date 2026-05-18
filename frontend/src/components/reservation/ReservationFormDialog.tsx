import {
    Dialog, DialogTitle, DialogContent, DialogActions,
    Button, TextField, Stack, MenuItem
} from '@mui/material';
import {useState, useEffect} from 'react';
import type {ReservationCreateDto} from '../../api/types/reservation.ts';
import {useAccommodations} from '../../hooks/useAccommodations.ts';
import {getCurrentUser} from '../../api/repositories/userRepository.ts';

interface ReservationFormDialogProps {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: ReservationCreateDto) => void;
}

const ReservationFormDialog = ({open, onClose, onSubmit}: ReservationFormDialogProps) => {
    const [accommodationId, setAccommodationId] = useState('');
    const [releaseAt, setReleaseAt] = useState('');
    const [userId, setUserId] = useState<number | null>(null);

    const {accommodations} = useAccommodations();

    useEffect(() => {
        getCurrentUser().then(res => setUserId(res.data.id));
    }, []);

    const handleSubmit = () => {
        if (!userId) return;
        onSubmit({
            accommodationId: Number(accommodationId),
            userId: userId,
            releaseAt: new Date(releaseAt).toISOString(),
        });
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>Add Reservation</DialogTitle>
            <DialogContent>
                <Stack spacing={2} sx={{mt: 1}}>
                    <TextField
                        select
                        label="Accommodation"
                        value={accommodationId}
                        onChange={e => setAccommodationId(e.target.value)}
                        fullWidth
                    >
                        {accommodations.map(a => (
                            <MenuItem key={a.id} value={a.id}>{a.name}</MenuItem>
                        ))}
                    </TextField>
                    <TextField
                        label="Release At"
                        type="datetime-local"
                        value={releaseAt}
                        onChange={e => setReleaseAt(e.target.value)}
                        fullWidth
                        slotProps={{ inputLabel: { shrink: true } }}
                    />
                </Stack>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button variant="contained" onClick={handleSubmit}>Reserve</Button>
            </DialogActions>
        </Dialog>
    );
}

export default ReservationFormDialog;