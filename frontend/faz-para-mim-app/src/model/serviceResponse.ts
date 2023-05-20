import { User } from "./user";
import { ProfileResponse } from "./profileResponse";

export interface ServiceResponse {
    id: number;
    date: string;
    hour: string;
    requestDetails: string;
    status: string;
    rating: number;
    client: User;
    provider: ProfileResponse;
}