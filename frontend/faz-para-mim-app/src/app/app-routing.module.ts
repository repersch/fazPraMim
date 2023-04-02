import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'

import { AboutComponent } from './components/AboutComponent/about.component'
import { NotFoundComponent } from './components/NotFoundComponent/page-not-found.component'
import { ServiceComponent } from './components/ServiceComponent/service.component'
import { UserComponent } from './components/UserComponent/user.component'

const routes: Routes = [
    { path: '', component: ServiceComponent },
    { path: 'about', component: AboutComponent },
    //{ path: '', component: HomeComponent },
    { path: 'service', component: ServiceComponent },
    // { path: 'user/:username', component: UserComponent } COLOCAR O MAIS ESPECÍFICO EM CIMA
    { path: 'user', component: UserComponent }
    // { path: '**', component: NotFoundComponent }
]

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }