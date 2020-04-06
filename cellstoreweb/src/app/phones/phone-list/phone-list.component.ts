import {Component, OnInit} from '@angular/core';
import {ApiService} from '../../api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-phone-list',
  templateUrl: './phone-list.component.html',
  styleUrls: ['./phone-list.component.css']
})
export class PhoneListComponent implements OnInit {

  phoneList: any = [];

  ngOnInit(): void {
    this.loadPhones();
  }

  constructor(private apiService: ApiService, private router: Router) {
  }

  loadPhones() {
    return this.apiService.listPhones()
      .subscribe(
        (data) => {
          this.phoneList = data;
          console.log(this.phoneList);
        });
  }

  deletePhone(data) {
    this.apiService.deletePhone(data._links.self.toString())
      .subscribe(res => {
          this.phoneList = this.phoneList.filter(p => p !== data);
        }
      );
  }

  addPhone() {
    this.router.navigateByUrl('phone-add');
  }

  editPhone(data) {
    window.localStorage.removeItem('editPhoneId');
    window.localStorage.setItem('editPhoneId', data.id.toString());
    this.router.navigateByUrl('phone-edit');
  }
}
