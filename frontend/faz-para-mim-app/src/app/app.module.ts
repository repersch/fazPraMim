import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { Footer } from './components/Footer/footer.component';
import { Header } from './components/Header/header.component';
import { Login } from './components/Login/login.component';
import { NavigationBar } from './components/NavigationBar/nav.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    Footer,
    Header,
    NavigationBar,
    Login
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
