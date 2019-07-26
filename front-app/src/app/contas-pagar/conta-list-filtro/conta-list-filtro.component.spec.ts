import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaListFiltroComponent } from './conta-list-filtro.component';

describe('ContaListFiltroComponent', () => {
  let component: ContaListFiltroComponent;
  let fixture: ComponentFixture<ContaListFiltroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContaListFiltroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContaListFiltroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
