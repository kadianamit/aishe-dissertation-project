import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report155Component } from './report155.component';

describe('Report155Component', () => {
  let component: Report155Component;
  let fixture: ComponentFixture<Report155Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report155Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report155Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
