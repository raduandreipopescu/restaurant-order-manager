import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductModel } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductsApiService {
  private url: string = 'http://localhost:8090/products';

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<any> {
    var result = this.httpClient.get(this.url);
    return result;
  }

  updateProduct(id: string, request: ProductModel): Observable<any> {
    return this.httpClient.put(this.url + '/' + id, request);
  }

  addProduct(request: ProductModel): Observable<any> {
    return this.httpClient.post(this.url, request);
  }

  deleteProduct(id: string): Observable<any> {
    return this.httpClient.delete(this.url + '/' + id, {
      responseType: 'text',
    });
  }
}
