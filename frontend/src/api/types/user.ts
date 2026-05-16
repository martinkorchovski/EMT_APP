export interface User {
    id: number;
    username: string;
    role: string;
}

export interface LoginDto {
    username: string;
    password: string;
}

export interface RegisterDto {
    username: string;
    password: string;
    repeatPassword: string;
}

export interface AuthResponse {
    token: string;
}