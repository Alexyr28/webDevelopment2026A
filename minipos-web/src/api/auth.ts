import { http } from "./http";
export type AuthResponse = {
  token: string;
  username: string;
};
export type LoginDto = {
  username: string;
  password: string;
};
export type RegisterDto = {
  username: string;
  password: string;
};
export const authApi = {
  login: (dto: LoginDto) =>
    http<AuthResponse>("/auth/login", {
      method: "POST",
      body: JSON.stringify(dto),
    }),
  register: (dto: RegisterDto) =>
    http<AuthResponse>("/auth/register", {
      method: "POST",
      body: JSON.stringify(dto),
    }),
};
