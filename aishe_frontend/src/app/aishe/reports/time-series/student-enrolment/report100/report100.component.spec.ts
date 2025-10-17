import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report100Component } from './report100.component';

describe('Report100Component', () => {
  let component: Report100Component;
  let fixture: ComponentFixture<Report100Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report100Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report100Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
