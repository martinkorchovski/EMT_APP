import { Typography } from '@mui/material';
import HostGrid from '../components/host/HostGrid.tsx';
import { useHosts } from '../hooks/useHosts.ts';

const HostsPage = () => {
    const { hosts, loading, error } = useHosts();

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <Typography variant='h4' sx={{ textAlign: 'center', mb: 2 }}>Hosts</Typography>
            <HostGrid hosts={hosts} />
        </div>
    );
};

export default HostsPage;