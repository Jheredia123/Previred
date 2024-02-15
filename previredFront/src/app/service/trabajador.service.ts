import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrabajadorService {

  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8080/api/trabajadores'; 


  getTrabajadores(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }


  getTrabajadoresById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

 
  createTrabajadores(trabajadorData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/creaTrabajador`, trabajadorData);
  }


  updateTrabajadores(trabajadorData: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${trabajadorData.id}`, trabajadorData);
  }

  deleteTrabajadores(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
    
  }

}
