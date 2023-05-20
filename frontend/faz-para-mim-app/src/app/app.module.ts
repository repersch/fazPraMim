import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CarouselComponent } from './components/CarouselComponent/carousel.component';
import { LoginCardComponent } from './components/LoginCardComponent/login-card.component';
import { NavigationComponent } from './components/NavigationComponent/navigation.component';
import { ProfileModalComponent } from './components/ProfileModalComponent/profile-modal.component';
import { ServiceComponent } from './components/ServiceComponent/service.component';
import { ServiceRequestsComponent } from './components/ServiceRequestsComponent/service-requests.component';
import { ProvidedServicesComponent } from './components/ProvidedServicesComponent/provided-services.component';
import { UserModalComponent } from './components/UserModalComponent/user-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    CarouselComponent,
    LoginCardComponent,
    NavigationComponent,
    ProfileModalComponent,
    ServiceComponent,
    ServiceRequestsComponent,
    ProvidedServicesComponent,
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
