import {Button, Card, CardContent, Typography} from '@mui/material';
import type { Host } from '../../api/types/host.ts';
import {useNavigate} from "react-router-dom";

interface HostCardProps {
    host: Host;
}

const HostCard = ({ host }: HostCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant='h5'>{host.name} {host.surname}</Typography>
                <Button
                    variant="contained"
                    size="small"
                    sx={{ mt: 1, alignSelf: 'flex-end' }}
                    onClick={() => navigate(`/hosts/${host.id}`)}
                >Details</Button>
            </CardContent>
        </Card>
    );
};

export default HostCard;