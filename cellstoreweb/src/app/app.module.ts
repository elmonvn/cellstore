import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {PageNotFoundComponent} from './misc/page-not-found/page-not-found.component';
import {PhoneListComponent} from './phones/phone-list/phone-list.component';
import {PhoneAddComponent} from './phones/phone-add/phone-add.component';
import {PhoneEditComponent} from './phones/phone-edit/phone-edit.component';
import {PhoneSearchComponent} from './phones/phone-search/phone-search.component';
import {ApiService} from './api.service';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    PhoneListComponent,
    PhoneAddComponent,
    PhoneEditComponent,
    PhoneSearchComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
