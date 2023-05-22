import { Component, Optional, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { OrderDetailsModel } from '../models/order-details.model';

@Component({
  selector: 'app-order-details-form',
  templateUrl: './order-details-form.component.html',
  styleUrls: ['./order-details-form.component.css'],
})
export class OrderDetailsFormComponent implements OnInit {
  orderDetailsForm = new FormGroup({
    productId: new FormControl('', Validators.required),
    productName: new FormControl(''),
    quantity: new FormControl('', Validators.required),
  });

  currentOrderDetails: OrderDetailsModel;

  constructor(
    public dialogRef: MatDialogRef<OrderDetailsFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    console.log(data);
    this.currentOrderDetails = data;
  }

  ngOnInit(): void {
    if (this.currentOrderDetails) {
      this.orderDetailsForm.controls.productId.setValue(
        this.currentOrderDetails.productId.toString()
      );
      this.orderDetailsForm.controls.productName.setValue(
        this.currentOrderDetails.productName
      );
      this.orderDetailsForm.controls.quantity.setValue(
        this.currentOrderDetails.quantity.toString()
      );
    }
  }

  onSubmit() {
    const updatedOrderDetails = {
      productId: this.orderDetailsForm.controls.productId.getRawValue(),
      quantity: this.orderDetailsForm.controls.quantity.getRawValue(),
    };
    if (this.currentOrderDetails) {
      this.dialogRef.close({ event: 'update', data: updatedOrderDetails });
    } else {
      this.dialogRef.close({ event: 'add', data: updatedOrderDetails });
    }
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
