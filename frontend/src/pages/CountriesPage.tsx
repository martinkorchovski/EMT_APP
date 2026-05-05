import { Typography } from '@mui/material';
import CountryGrid from '../components/country/CountryGrid.tsx';
import { useCountries } from '../hooks/useCountries.ts';

const CountriesPage = () => {
    const { countries, loading, error } = useCountries();

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <Typography variant='h4' sx={{ textAlign: 'center', mb: 2 }}>Countries</Typography>
            <CountryGrid countries={countries} />
        </div>
    );
};

export default CountriesPage;