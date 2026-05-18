import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import { isAuthenticated, logout } from '../../utils/auth.ts';

const Header = () => {
    const navigate = useNavigate();
    const authenticated = isAuthenticated();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Accommodation App
                </Typography>
                <Button color="inherit" component={Link} to="/">Home</Button>
                {authenticated && (
                    <>
                        <Button color="inherit" component={Link} to="/accommodations">Accommodations</Button>
                        <Button color="inherit" component={Link} to="/hosts">Hosts</Button>
                        <Button color="inherit" component={Link} to="/countries">Countries</Button>
                        <Button color="inherit" component={Link} to="/users">Users</Button>
                        <Button color="inherit" component={Link} to="/reservations">Reservations</Button>
                        <Button color="inherit" onClick={handleLogout}>Logout</Button>
                    </>
                )}
                {!authenticated && (
                    <Button color="inherit" component={Link} to="/login">Login</Button>
                )}
            </Toolbar>
        </AppBar>
    );
};

export default Header;