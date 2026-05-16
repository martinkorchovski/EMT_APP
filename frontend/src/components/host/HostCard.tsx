import { Button, Card, CardContent, Typography, Stack } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import type { Host } from '../../api/types/host.ts';
import { useNavigate } from 'react-router-dom';

interface HostCardProps {
    host: Host;
    onEdit: (host: Host) => void;
    onDelete: (id: number) => void;
    isAdmin: boolean;
}

const HostCard = ({ host, onEdit, onDelete, isAdmin }: HostCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant="h5">{host.name} {host.surname}</Typography>
                <Typography variant="subtitle1">{host.countryName}</Typography>
                <Stack direction="row" spacing={1} sx={{ mt: 1 }}>
                    <Button
                        variant="contained"
                        size="small"
                        onClick={() => navigate(`/hosts/${host.id}`)}
                    >
                        Details
                    </Button>
                    {isAdmin && (
                        <>
                            <Button
                                variant="outlined"
                                size="small"
                                startIcon={<EditIcon />}
                                onClick={() => onEdit(host)}
                            >
                                Edit
                            </Button>
                            <Button
                                variant="contained"
                                color="error"
                                size="small"
                                startIcon={<DeleteIcon />}
                                onClick={() => onDelete(host.id)}
                            >
                                Delete
                            </Button>
                        </>
                    )}
                </Stack>
            </CardContent>
        </Card>
    );
};

export default HostCard;