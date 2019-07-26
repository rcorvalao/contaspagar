import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContasPagar } from '../models';

@Injectable({
  providedIn: 'root'
})
export class ContasPagarService {

  constructor(
        private httpClient: HttpClient
  ) {
  }

  obtemContasPagar(): Observable<ContasPagar[]> {
    const url = `http://localhost:8081/v1/contaspagar`;
    return this.httpClient.get<ContasPagar[]>(url);
  }

  criarContaPagar(contaPagar) {
    const url = `http://localhost:8081/v1/contaspagar`;
    return this.httpClient.post(url, contaPagar);
  }

}
