import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StateWiseMoniteringComponent } from './state-wise-monitoring.component';

describe('StateWiseMoniteringComponent', () => {
  let component: StateWiseMoniteringComponent;
  let fixture: ComponentFixture<StateWiseMoniteringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StateWiseMoniteringComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StateWiseMoniteringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
