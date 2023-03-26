import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'

import { Login } from './components/Login/login.component'
import { ServiceComponentPage } from './components/Service/service.component'
import { UserComponentPage } from './components/User/user.component'

const routes: Routes = [
    { path: '', component: Login },
    { path: 'service', component: ServiceComponentPage },
    { path: 'user', component: UserComponentPage }
]

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }