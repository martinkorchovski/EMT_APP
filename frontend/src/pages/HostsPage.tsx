import {useState} from 'react';
import {Typography, Button, Box} from '@mui/material';
import HostGrid from '../components/host/HostGrid.tsx';
import HostFormDialog from '../components/host/HostFormDialog.tsx';
import {useHosts} from '../hooks/useHosts.ts';
import type {Host, HostCreateDto, HostUpdateDto} from '../api/types/host.ts';
import {isAdmin} from '../utils/auth.ts';

const HostsPage = () => {
    const {hosts, loading, error, addHost, editHost, removeHost} = useHosts();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [selected, setSelected] = useState<Host | null>(null);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleAdd = () => {
        setSelected(null);
        setDialogOpen(true);
    };

    const handleEdit = (host: Host) => {
        setSelected(host);
        setDialogOpen(true);
    };

    const handleDelete = async (id: number) => {
        await removeHost(id);
    };

    const handleSubmit = async (data: HostCreateDto | HostUpdateDto) => {
        if (selected) {
            await editHost(selected.id, data as HostUpdateDto);
        } else {
            await addHost(data as HostCreateDto);
        }
    };

    return (
        <div>
            <Box sx={{position: 'relative', display: 'flex', justifyContent: 'center', alignItems: 'center', mb: 3}}>
                <Typography variant="h4">Hosts</Typography>
                {isAdmin() && (
                    <Button variant="outlined" size="large" onClick={handleAdd} sx={{position: 'absolute', right: 0}}>
                        Add Host
                    </Button>
                )}
            </Box>

            <HostGrid
                hosts={hosts}
                onEdit={handleEdit}
                onDelete={handleDelete}
                isAdmin={isAdmin()}
            />

            <HostFormDialog
                open={dialogOpen}
                onClose={() => setDialogOpen(false)}
                onSubmit={handleSubmit}
                existing={selected}
            />
        </div>
    );
};

export default HostsPage;