import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockSymbolComponent } from './stock-symbol.component';

describe('StockSymbolComponent', () => {
  let component: StockSymbolComponent;
  let fixture: ComponentFixture<StockSymbolComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockSymbolComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockSymbolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
