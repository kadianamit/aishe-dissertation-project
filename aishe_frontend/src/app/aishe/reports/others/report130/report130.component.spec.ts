import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report130Component } from './report130.component';

describe('Report130Component', () => {
  let component: Report130Component;
  let fixture: ComponentFixture<Report130Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report130Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report130Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
