import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsApiService } from '../services/products-api.service';
import { ProductModel } from '../models/product.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ProductFormComponent } from '../product-form/product-form.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  displayedColumns: string[] = [
    'id',
    'name',
    'description',
    'quantity',
    'price',
    'actions',
  ];
  dataSource: any;

  constructor(
    private productsApi: ProductsApiService,
    private dialogRef: MatDialog
  ) {}

  ngOnInit(): void {
    this.productsApi.getAll().subscribe((res) => {
      console.log(res);
      this.dataSource = new MatTableDataSource<ProductModel>(
        res.map((product: any) => {
          return {
            id: product.id,
            name: product.name,
            description: product.description,
            quantity: product.quantity,
            price: product.price,
          };
        })
      );
      this.dataSource.paginator = this.paginator;
    });
  }

  openDialog(product?: ProductModel): void {
    const dialogRef = this.dialogRef.open(ProductFormComponent, {
      width: '500px',
      backdropClass: 'custom-dialog-backdrop-class',
      panelClass: 'custom-dialog-panel-class',
      data: product,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result.event === 'submit' && product) {
        this.productsApi
          .updateProduct(product.id.toString(), result.data)
          .subscribe();
        location.reload();
      } else if (result.event === 'add') {
        this.productsApi.addProduct(result.data).subscribe();
        location.reload();
      }
    });
  }

  deleteProduct(id: string): void {
    this.productsApi.deleteProduct(id).subscribe();
    location.reload();
  }
}
