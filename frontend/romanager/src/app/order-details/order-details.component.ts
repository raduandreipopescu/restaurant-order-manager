import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { OrderDetailsApiService } from '../services/order-details-api.service';
import { OrderDetailsModel } from '../models/order-details.model';
import { ActivatedRoute } from '@angular/router';
import { OrderDetailsFormComponent } from '../order-details-form/order-details-form.component';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css'],
})
export class OrderDetailsComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  displayedColumns: string[] = ['productName', 'quantity', 'actions'];
  dataSource: any;
  currentOrderId: string;

  constructor(
    private orderDetailsApi: OrderDetailsApiService,
    private route: ActivatedRoute,
    private dialogRef: MatDialog
  ) {
    this.currentOrderId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.orderDetailsApi
      .getAllByOrderId(this.currentOrderId)
      .subscribe((res) => {
        console.log(res);
        this.dataSource = new MatTableDataSource<OrderDetailsModel>(
          res.map((orderDetails: any) => {
            return {
              id: orderDetails.id,
              orderId: orderDetails.order.id,
              productId: orderDetails.product.id,
              productName: orderDetails.product.name,
              quantity: orderDetails.quantity,
            };
          })
        );
        this.dataSource.paginator = this.paginator;
      });
  }

  openDialog(orderDetails?: OrderDetailsModel): void {
    const dialogRef = this.dialogRef.open(OrderDetailsFormComponent, {
      width: '500px',
      backdropClass: 'custom-dialog-backdrop-class',
      panelClass: 'custom-dialog-panel-class',
      data: orderDetails,
    });

    dialogRef.afterClosed().subscribe((result) => {
      result.data.orderId = this.currentOrderId;
      if (result.event === 'update' && orderDetails) {
        this.orderDetailsApi
          .updateOrderDetails(orderDetails.id.toString(), result.data)
          .subscribe();
        location.reload();
      } else if (result.event === 'add') {
        this.orderDetailsApi.addOrderDetails(result.data).subscribe();
        location.reload();
      }
    });
  }

  deleteOrderDetails(id: string): void {
    this.orderDetailsApi.deleteOrderDetails(id).subscribe();
    location.reload();
  }
}
