import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Phone} from './model/phone.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8080/api/phones';

  constructor(private httpClient: HttpClient) {
  }

  // HTTP Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };


  listPhones(): Observable<Phone> {
    return this.httpClient.get<Phone>(this.baseUrl)
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      );
  }

  listPhone(code): Observable<Phone> {
    return this.httpClient.get<Phone>(this.baseUrl + '/' + code)
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      );
  }

  addPhone(data) {
    return this.httpClient.post<Phone>(this.baseUrl, JSON.stringify(data), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      );
  }

  editPhone(code, data) {
    return this.httpClient.put<Phone>(this.baseUrl + '/' + code, JSON.stringify(data), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      );
  }

  searchPhone() {

  }

  deletePhone(code) {
    // return this.httpClient.delete<Phone>(this.baseUrl + '/' + code, this.httpOptions)
    return this.httpClient.delete<Phone>(this.baseUrl + '/' + code, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      );
  }

  errorHandler(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Código de erro: ${error.status}\nMensagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
