import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report119Component } from './report119.component';

describe('Report119Component', () => {
  let component: Report119Component;
  let fixture: ComponentFixture<Report119Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report119Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report119Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
