import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

private apiUrl = 'http://localhost:8080/api/empresas'; // URL de tu API

  constructor(private http: HttpClient) { }

  getEmpresa(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  // Obtener una empresa por su ID
  getEmpresaById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Crear una nueva empresa
  createEmpresa(empresaData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/`, empresaData);
  }

  // Actualizar una empresa existente
  updateEmpresa(empresaData: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${empresaData.id}`, empresaData);
  }

  // Eliminar una empresa
  deleteEmpresa(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
    
  }

}