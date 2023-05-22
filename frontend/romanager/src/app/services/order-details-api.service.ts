import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderDetailsModel } from '../models/order-details.model';

@Injectable({
  providedIn: 'root',
})
export class OrderDetailsApiService {
  private url: string = 'http://localhost:8090/order-details';

  constructor(private httpClient: HttpClient) {}

  getAllByOrderId(id: string): Observable<any> {
    var result = this.httpClient.get(this.url + '/' + id);
    return result;
  }

  addOrderDetails(request: OrderDetailsModel): Observable<any> {
    return this.httpClient.post(this.url, request);
  }

  updateOrderDetails(id: string, request: OrderDetailsModel): Observable<any> {
    return this.httpClient.put(this.url + '/' + id, request);
  }

  deleteOrderDetails(id: string): Observable<any> {
    return this.httpClient.delete(this.url + '/' + id);
  }
}
