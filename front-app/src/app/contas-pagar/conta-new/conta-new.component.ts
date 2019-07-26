import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContasPagarService } from 'src/app/core/services/contas-pagar.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-conta-new',
  templateUrl: './conta-new.component.html',
  styleUrls: ['./conta-new.component.scss']
})
export class ContaNewComponent implements OnInit {

  conataPagarForm: FormGroup;

  constructor(
      private contasPagarService: ContasPagarService,
      private router: Router,
      private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.conataPagarForm = this.formBuilder.group({
      nome: this.formBuilder.control('', [Validators.required]),
      valorOriginal: this.formBuilder.control('', [Validators.required]),
      dataVencimento: this.formBuilder.control('', [Validators.required]),
      dataPagamento: this.formBuilder.control('', [Validators.required])
    });
  }


  criarContaPagar(contaForm) {
    console.log('criar conta', contaForm);

    const resposta = this.contasPagarService.criarContaPagar(contaForm);
    resposta.subscribe(
      data  => {
        console.log('POST Request is successful', data);
        alert('Conta a pagar incluida!');
      },
      error  => {
        console.log('Error', error);
        alert(error.message);
      }
    );

  }

}
