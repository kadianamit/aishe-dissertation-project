import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report60Component } from './report60.component';

describe('Report60Component', () => {
  let component: Report60Component;
  let fixture: ComponentFixture<Report60Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report60Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report60Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
