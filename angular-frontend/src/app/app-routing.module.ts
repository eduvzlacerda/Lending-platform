import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import {ArticleListComponent} from "./article-list/article-list.component";
import {ArticleDetailComponent} from "./article-detail/article-detail.component";
import {RequestsComponent} from "./requests/requests.component";
import {MyRequestsComponent} from "./requests/myRequests.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'articles', component: ArticleListComponent },
  { path: 'article/:id', component: ArticleDetailComponent},
  { path: 'requests', component: RequestsComponent },
  { path: 'myRequests', component: MyRequestsComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'articles', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
