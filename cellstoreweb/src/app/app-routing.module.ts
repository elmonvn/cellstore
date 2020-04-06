import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PhoneListComponent } from './phones/phone-list/phone-list.component';
import { PhoneAddComponent } from './phones/phone-add/phone-add.component';
import { PhoneEditComponent } from './phones/phone-edit/phone-edit.component';
import { PhoneSearchComponent } from './phones/phone-search/phone-search.component';
import { PageNotFoundComponent } from './misc/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'phone-list', pathMatch: 'full'},
  { path: 'phone-list', component: PhoneListComponent },
  { path: 'phone-add', component: PhoneAddComponent },
  { path: 'phone-edit', component: PhoneEditComponent },
  { path: 'phone-search', component: PhoneSearchComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
