import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';
import {NavigationComponent} from "./navigation/navigation.component";
import {SafeHtml} from "./_helpers/SafeHtml";
import {FilterPipe} from "./_helpers/FilterPipe";
import {RequestsComponent} from "./requests/requests.component";
import {MyRequestsComponent} from "./requests/myRequests.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ArticleListComponent,
    ArticleDetailComponent,
    RequestsComponent,
    MyRequestsComponent,
    NavigationComponent,
    SafeHtml,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
