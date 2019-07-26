import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaNewComponent } from './conta-new.component';

describe('ContaNewComponent', () => {
  let component: ContaNewComponent;
  let fixture: ComponentFixture<ContaNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContaNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContaNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
