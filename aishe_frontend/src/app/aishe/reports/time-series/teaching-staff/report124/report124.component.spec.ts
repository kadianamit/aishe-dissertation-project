import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report124Component } from './report124.component';

describe('Report124Component', () => {
  let component: Report124Component;
  let fixture: ComponentFixture<Report124Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report124Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report124Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
