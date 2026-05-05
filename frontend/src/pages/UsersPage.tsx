import UserGrid from "../components/user/UserGrid.tsx";
import {useUsers} from "../hooks/useUsers.ts";
import {Typography} from "@mui/material";

const UserPage = () => {
    const { users, loading, error } = useUsers();

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <Typography variant='h4' sx={{ textAlign: 'center', mb: 2 }}>Users</Typography>
            <UserGrid users={users} />
        </div>
    );
};

export default UserPage;