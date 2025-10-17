import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchbyAisheComponent } from './searchby-aishe.component';

describe('SearchbyAisheComponent', () => {
  let component: SearchbyAisheComponent;
  let fixture: ComponentFixture<SearchbyAisheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchbyAisheComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchbyAisheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
