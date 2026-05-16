export interface Host {
    id: number;
    name: string;
    surname: string;
    countryName: string;
}

export interface HostCreateDto {
    name: string;
    surname: string;
    countryId: number;
}

export interface HostUpdateDto {
    name?: string;
    surname?: string;
    countryId?: number;
}