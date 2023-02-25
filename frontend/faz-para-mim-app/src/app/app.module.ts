import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { Footer } from './components/Footer/footer.component';
import { Header } from './components/Header/header.component';
import { Login } from './components/Login/login.component';
import { NavigationBar } from './components/NavigationBar/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    Footer,
    Header,
    NavigationBar,
    Login
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
