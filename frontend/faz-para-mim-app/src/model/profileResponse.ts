import { User } from "./user";
import { ServiceType } from "./serviceType";

export interface ProfileResponse {
    id: number;
    description: string;
    city: string;
    user: User;
    serviceType: ServiceType;
    rating: number;
}