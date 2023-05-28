import { ServiceType } from "./serviceType";
import { User } from "./user";

export interface ProfileResponse {
    id: number;
    description: string;
    city: string;
    user: User;
    serviceType: ServiceType;
    rating: number;
}