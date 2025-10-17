import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report62Component } from './report62.component';

describe('Report62Component', () => {
  let component: Report62Component;
  let fixture: ComponentFixture<Report62Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report62Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report62Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
