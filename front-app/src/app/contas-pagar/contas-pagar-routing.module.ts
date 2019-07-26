import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContaListComponent } from './conta-list/conta-list.component';
import { ContaNewComponent } from './conta-new/conta-new.component';


const routes: Routes = [
    { path: 'listarcontaspagar', component: ContaListComponent }
    , { path: 'criarcontaspagar', component: ContaNewComponent }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContasPagarRoutingModule { }
