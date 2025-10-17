import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report114Component } from './report114.component';

describe('Report114Component', () => {
  let component: Report114Component;
  let fixture: ComponentFixture<Report114Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report114Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report114Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
