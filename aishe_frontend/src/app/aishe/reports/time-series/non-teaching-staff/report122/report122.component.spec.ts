import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report122Component } from './report122.component';

describe('Report122Component', () => {
  let component: Report122Component;
  let fixture: ComponentFixture<Report122Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report122Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report122Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
