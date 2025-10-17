import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report102Component } from './report102.component';

describe('Report102Component', () => {
  let component: Report102Component;
  let fixture: ComponentFixture<Report102Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report102Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report102Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
