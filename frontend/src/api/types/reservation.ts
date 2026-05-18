export interface Reservation {
    id: number;
    accommodation: {
        id: number;
        name: string;
    };
    user: {
        id: number;
        username: string;
    };
    reservedAt: string;
    releaseAt: string;
}

export interface ReservationCreateDto {
    accommodationId: number;
    userId: number;
    releaseAt: string;
}