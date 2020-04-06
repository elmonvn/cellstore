import {Component, NgZone, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {ApiService} from '../../api.service';

@Component({
  selector: 'app-phone-add',
  templateUrl: './phone-add.component.html',
  styleUrls: ['./phone-add.component.css']
})
export class PhoneAddComponent implements OnInit {

  phoneForm: FormGroup;

  phoneArr: any = [];

  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    public apiService: ApiService
  ) {
  }

  ngOnInit(): void {
    this.addPhone();
  }

  addPhone() {
    this.phoneForm = this.fb.group({
      phone_code: [''],
      phone_model: [''],
      phone_brand: [''],
      phone_price: [''],
      phone_photo: [''],
      phone_start_date: [''],
      phone_end_date: [''],
      phone_color: ['']
    });
  }

  submitForm() {
    this.apiService.addPhone(this.phoneForm.value)
      .subscribe(res => {
        this.ngZone.run(() => this.router.navigateByUrl('phone-list'));
      });
  }
}
