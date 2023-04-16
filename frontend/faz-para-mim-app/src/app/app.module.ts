import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CarouselComponent } from './components/CarouselComponent/carousel.component';
import { LoginCardComponent } from './components/LoginCardComponent/login-card.component';
import { NavigationComponent } from './components/NavigationComponent/navigation.component';
import { UserModalComponent } from './components/UserModalComponent/user-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    CarouselComponent,
    LoginCardComponent,
    NavigationComponent,
    UserModalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
