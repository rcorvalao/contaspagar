import { Component, OnInit } from '@angular/core';
import { ContasPagar } from 'src/app/core/models';
import { ContasPagarService } from 'src/app/core/services/contas-pagar.service';

@Component({
  selector: 'app-conta-list',
  templateUrl: './conta-list.component.html',
  styleUrls: ['./conta-list.component.scss']
})
export class ContaListComponent implements OnInit {

  contasPagar: ContasPagar[];

  constructor(private contasPagarService: ContasPagarService) {
  }

  ngOnInit() {
    this.contasPagarService.obtemContasPagar().subscribe(
      contatosDaAgenda => this.contasPagar = contatosDaAgenda
    );
  }

  removerConta(conta): void {
    console.log('remover conta.', conta);
  }

  editarConta(conta): void {
    console.log('editar conta.', conta);
  }


}
