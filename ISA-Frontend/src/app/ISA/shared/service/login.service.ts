import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Login } from "../model/Login";
import { User } from "../model/User";

@Injectable()
export class LoginService {
    private pacijetUrl: string;
    adapter: any;


    constructor(private http: HttpClient) {
    }

    public getLoggedInUser(): Observable<User> {
        return this.http.get<User>("/api/pharmacy/whoIsLoggedIn");
    }

    public login(loginRequest: Login) {
        return this.http.post<Response>("/api/pharmacy/login", loginRequest);
    }
    public logout(request: Request) {
        return this.http.post("/api/pharmacy/logout", request);
    }


    public changePassword(id:number, newPassword:string, userRole:string){
        return this.http.put<User>("/api/pharmacy/changePassword/" + userRole +"/"+ newPassword, id);
    }

}