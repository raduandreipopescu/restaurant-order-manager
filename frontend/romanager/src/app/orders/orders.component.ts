import { Component, OnInit, ViewChild } from '@angular/core';
import { OrdersApiService } from '../services/orders-api.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { OrderFormComponent } from '../order-form/order-form.component';
import { OrderModel } from '../models/order.model';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  displayedColumns: string[] = ['id', 'table', 'status', 'value', 'actions'];
  dataSource: any;

  constructor(
    private ordersApi: OrdersApiService,
    private dialogRef: MatDialog
  ) {}

  ngOnInit(): void {
    this.ordersApi.getAll().subscribe((res) => {
      console.log(res);
      this.dataSource = new MatTableDataSource<OrderModel>(
        res.map((order: any) => {
          return {
            id: order.id,
            tableName: order.tableName,
            status: order.status,
            finalValue: order.finalValue,
          };
        })
      );
      this.dataSource.paginator = this.paginator;
    });
  }

  openDialog(): void {
    const dialogRef = this.dialogRef.open(OrderFormComponent, {
      width: '500px',
      backdropClass: 'custom-dialog-backdrop-class',
      panelClass: 'custom-dialog-panel-class',
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ordersApi.addOrder(result.data).subscribe();
      location.reload();
    });
  }

  closeOrder(id: string): void {
    this.ordersApi.closeOrder(id).subscribe();
    location.reload();
  }
}
