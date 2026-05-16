export interface Accommodation {
    id: number;
    name: string;
    numRooms: number;
    category: string;
    state: string;
    host: string;
    country: string;
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

export interface AccommodationCreateDto {
    name: string;
    numRooms: number;
    categoryId: number;
    stateId: number;
    hostId: number;
}

export interface AccommodationUpdateDto {
    name?: string;
    numRooms?: number;
    categoryId?: number;
    stateId?: number;
    hostId?: number;
}