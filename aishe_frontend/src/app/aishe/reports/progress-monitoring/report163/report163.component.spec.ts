import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report163Component } from './report163.component';

describe('Report163Component', () => {
  let component: Report163Component;
  let fixture: ComponentFixture<Report163Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report163Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report163Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
