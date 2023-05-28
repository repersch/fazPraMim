import { ProfileResponse } from "./profileResponse";
import { User } from "./user";

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