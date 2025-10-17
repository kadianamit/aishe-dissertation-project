import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report169Component } from './report169.component';

describe('Report169Component', () => {
  let component: Report169Component;
  let fixture: ComponentFixture<Report169Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report169Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report169Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
