import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderModel } from '../models/order.model';

@Injectable({
  providedIn: 'root',
})
export class OrdersApiService {
  private url: string = 'http://localhost:8090/orders';

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<any> {
    var result = this.httpClient.get(this.url);
    return result;
  }

  closeOrder(id: string): Observable<any> {
    return this.httpClient.put(this.url + '/' + id, null);
  }

  addOrder(request: OrderModel): Observable<any> {
    return this.httpClient.post(this.url, request);
  }
}
