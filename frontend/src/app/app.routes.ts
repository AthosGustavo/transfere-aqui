import { Routes } from '@angular/router';
import { PrincipalPageComponent } from './pages/principal-page/principal-page.component';

export const routes: Routes = [
    {
        pathMatch:'full',
        path: '',
        component: PrincipalPageComponent    
    }
];
