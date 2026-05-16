export interface Country {
    id: number;
    name: string;
    continent: string;
}

export interface CountryCreateDto {
    name: string;
    continent: string;
}

export interface CountryUpdateDto {
    name?: string;
    continent?: string;
}