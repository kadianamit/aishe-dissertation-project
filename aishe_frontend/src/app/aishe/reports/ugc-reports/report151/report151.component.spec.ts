import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report151Component } from './report151.component';

describe('Report151Component', () => {
  let component: Report151Component;
  let fixture: ComponentFixture<Report151Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report151Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report151Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
