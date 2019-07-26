import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-conta-list-filtro',
  templateUrl: './conta-list-filtro.component.html',
  styleUrls: ['./conta-list-filtro.component.scss']
})
export class ContaListFiltroComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  adicionarContaPagar(): void {
    console.log('Adicinar conta');
    this.router.navigate(['/criarcontaspagar']);
  }

}
