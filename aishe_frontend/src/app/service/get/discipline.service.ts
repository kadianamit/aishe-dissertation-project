import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*'
  })
}

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

  constructor(private http: HttpClient) { }

  getDisciplineGroup(): Observable<any> {
    return this.http.get(environment.baseUrl + 'getDisciplineGroup');
  }
  getDisciplineName(data: any) {
    return this.http.get(environment.baseUrl + `getDisciplineNameByGroup?groupId=${data}`, httpOptions);
  }

}
