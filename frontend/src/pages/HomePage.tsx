import { Typography, Container } from '@mui/material';

const HomePage = () => {
    return (
        <Container>
            <Typography variant="h4" gutterBottom sx={{ textAlign: 'center' }}>
                Welcome to Accommodation App
            </Typography>
        </Container>
    );
};

export default HomePage;