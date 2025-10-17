import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report105Component } from './report105.component';

describe('Report105Component', () => {
  let component: Report105Component;
  let fixture: ComponentFixture<Report105Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report105Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report105Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
