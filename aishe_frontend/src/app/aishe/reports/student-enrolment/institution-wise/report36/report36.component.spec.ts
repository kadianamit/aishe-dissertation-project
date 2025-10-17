import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report36Component } from './report36.component';

describe('Report36Component', () => {
  let component: Report36Component;
  let fixture: ComponentFixture<Report36Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report36Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report36Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
