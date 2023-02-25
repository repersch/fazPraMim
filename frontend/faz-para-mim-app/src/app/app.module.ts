import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { Footer } from './components/Footer/footer.component';
import { Header } from './components/Header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    Footer,
    Header
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
