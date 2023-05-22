import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css'],
})
export class OrderFormComponent {
  orderForm = new FormGroup({
    table: new FormControl('', Validators.required),
  });

  constructor(public dialogRef: MatDialogRef<OrderFormComponent>) {}

  onSubmit() {
    console.log('Submit');
    const addedOrder = {
      tableName: this.orderForm.controls.table.getRawValue(),
    };
    this.dialogRef.close({ event: 'add', data: addedOrder });
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
