import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRegistartionComponent } from './new-registartion.component';

describe('NewRegistartionComponent', () => {
  let component: NewRegistartionComponent;
  let fixture: ComponentFixture<NewRegistartionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewRegistartionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRegistartionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
