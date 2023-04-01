export interface Token {
    id: number;
    username: string;
    authenticated: boolean;
    created: Date;
    expiration: Date;
    accessToken: string;
    refreshToken: string;
}