import { NgModule } from '@angular/core';
import { ContaListComponent } from './conta-list/conta-list.component';
import { ContaListFiltroComponent } from './conta-list-filtro/conta-list-filtro.component';
import { ContaNewComponent } from './conta-new/conta-new.component';
import { SharedModule } from '../shared/shared.module';
import { ContasPagarRoutingModule } from './contas-pagar-routing.module';

@NgModule({
  declarations: [ContaListComponent, ContaListFiltroComponent, ContaNewComponent],
  imports: [
    SharedModule,
    ContasPagarRoutingModule
  ]
})
export class ContasPagarModule { }
