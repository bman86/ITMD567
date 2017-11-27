import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StksymbolComponent } from './stksymbol.component';

describe('StksymbolComponent', () => {
  let component: StksymbolComponent;
  let fixture: ComponentFixture<StksymbolComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StksymbolComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StksymbolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
