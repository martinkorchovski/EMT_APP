import {Button, Card, CardContent, Typography} from '@mui/material';
import type {User} from "../../api/types/user.ts";
import {useNavigate} from "react-router-dom";

interface UserCardProps {
    user: User;
}

const UserCard = ({ user }: UserCardProps) => {
    const navigate = useNavigate();

    return (
        <Card sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography variant='h5'>{user.username}</Typography>
                <Button
                    variant="contained"
                    size="small"
                    sx={{ mt: 1, alignSelf: 'flex-end' }}
                    onClick={() => navigate(`/users/${user.id}`)}
                >Details</Button>
            </CardContent>
        </Card>
    );
};

export default UserCard;
