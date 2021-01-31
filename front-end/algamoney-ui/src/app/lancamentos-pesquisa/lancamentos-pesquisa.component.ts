import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})
export class LancamentosPesquisaComponent implements OnInit {

  lancamentos = [
    { tipo: 'DESPESA', descricao: 'Compra de pão', dataVencimento: '06/02/1995',
     dataPagamento: '07/03/1998', valor: 14332, pessoa: 'Atacodo Brasil'},
     { tipo: 'DESPESA', descricao: 'Compra de pão', dataVencimento: '06/02/1995',
     dataPagamento: '07/03/1998', valor: 14332, pessoa: 'Atacodo Brasil'},
     { tipo: 'DESPESA', descricao: 'Compra de pão', dataVencimento: '06/02/1995',
     dataPagamento: '07/03/1998', valor: 14332, pessoa: 'Atacodo Brasil'},
     { tipo: 'DESPESA', descricao: 'Compra de pão', dataVencimento: '06/02/1995',
     dataPagamento: '07/03/1998', valor: 14332, pessoa: 'Atacodo Brasil'}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
