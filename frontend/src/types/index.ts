export interface Country {
    id: number;
    name: string;
    continent: string;
}

export interface Host {
    id: number;
    name: string;
    surname: string;
    countryName: string;
}

export interface Accommodation {
    id: number;
    name: string;
    numRooms: number;
    category: string;
    state: string;
    host: string;
    country: string;
    isRented: boolean;
}

export interface AccommodationDetail {
    id: number;
    name: string;
    numRooms: number;
    category: { id: number; name: string };
    state: { id: number; name: string };
    host: { id: number; name: string; surname: string };
    isRented: boolean;
}