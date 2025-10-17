import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report103Component } from './report103.component';

describe('Report103Component', () => {
  let component: Report103Component;
  let fixture: ComponentFixture<Report103Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report103Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report103Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
