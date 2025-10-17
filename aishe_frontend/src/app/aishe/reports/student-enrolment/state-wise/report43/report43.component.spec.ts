import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report43Component } from './report43.component';

describe('Report43Component', () => {
  let component: Report43Component;
  let fixture: ComponentFixture<Report43Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report43Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report43Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
