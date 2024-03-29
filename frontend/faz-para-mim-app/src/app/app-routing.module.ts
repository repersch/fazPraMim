import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './components/AboutComponent/about.component';
import { ProfileComponent } from './components/ProfileComponent/profile.component';
import { ProvidedServicesComponent } from './components/ProvidedServicesComponent/provided-services.component';
import { ServiceComponent } from './components/ServiceComponent/service.component';
import { ServiceRequestsComponent } from './components/ServiceRequestsComponent/service-requests.component';
import { UserComponent } from './components/UserComponent/user.component';

const routes: Routes = [
    { path: '', component: ServiceComponent },
    { path: 'about', component: AboutComponent },
    { path: 'profile/:id', component: ProfileComponent },
    { path: 'service', component: ServiceComponent },
    { path: 'services/client/:id', component: ServiceRequestsComponent },
    { path: 'services/provider/:id', component: ProvidedServicesComponent },
    { path: 'user/:id', component: UserComponent },
    { path: 'user', component: UserComponent }
]

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }