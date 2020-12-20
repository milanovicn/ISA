import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";


@Injectable()
export class UserService {
    private pacijetUrl: string;
    adapter: any;


    constructor(private http: HttpClient) {
    }

    public update(updatedUser:User){
        return this.http.put("/api/user/edit", updatedUser);
    }

    public getAllergies(user_id:number):Observable<Medicine[]>{
      return this.http.get<Medicine[]>("/api/user/allergies/"+user_id);
    }

     public addAllergy(updatedUser:User, medicine_id:number){
      return this.http.put<User>("/api/user/allergy/4" + medicine_id, updatedUser);
    }

}