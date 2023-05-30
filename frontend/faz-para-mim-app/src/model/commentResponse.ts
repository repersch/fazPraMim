import { ProfileResponse } from "./profileResponse";
import { User } from "./user";

export interface CommentResponse {
    id: number;
    client: User;
    provider: ProfileResponse;
    comment: string;
}