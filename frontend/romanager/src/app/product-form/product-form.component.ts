import { Component, Optional, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProductModel } from '../models/product.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent implements OnInit {
  productForm = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    quantity: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
  });

  currentProduct: ProductModel;

  constructor(
    public dialogRef: MatDialogRef<ProductFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    console.log(data);
    this.currentProduct = data;
  }

  ngOnInit(): void {
    if (this.currentProduct) {
      this.productForm.controls.name.setValue(this.currentProduct.name);
      this.productForm.controls.description.setValue(
        this.currentProduct.description
      );
      this.productForm.controls.quantity.setValue(
        this.currentProduct.quantity.toString()
      );
      this.productForm.controls.price.setValue(
        this.currentProduct.price.toString()
      );
    }
  }

  onSubmit() {
    console.log('Submit');
    const updatedProduct = {
      name: this.productForm.controls.name.getRawValue(),
      description: this.productForm.controls.description.getRawValue(),
      quantity: this.productForm.controls.quantity.getRawValue(),
      price: this.productForm.controls.price.getRawValue(),
    };
    if (this.currentProduct) {
      this.dialogRef.close({ event: 'submit', data: updatedProduct });
    } else {
      this.dialogRef.close({ event: 'add', data: updatedProduct });
    }
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
