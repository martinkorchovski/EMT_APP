import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Accommodation App
                </Typography>
                <Button color="inherit" component={Link} to="/">Home</Button>
                <Button color="inherit" component={Link} to="/accommodations">Accommodations</Button>
                <Button color="inherit" component={Link} to="/hosts">Hosts</Button>
                <Button color="inherit" component={Link} to="/countries">Countries</Button>
            </Toolbar>
        </AppBar>
    );
};

export default Header;