import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report164Component } from './report164.component';

describe('Report164Component', () => {
  let component: Report164Component;
  let fixture: ComponentFixture<Report164Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report164Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report164Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
