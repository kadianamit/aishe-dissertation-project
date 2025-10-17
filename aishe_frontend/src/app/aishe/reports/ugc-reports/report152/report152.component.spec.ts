import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report152Component } from './report152.component';

describe('Report152Component', () => {
  let component: Report152Component;
  let fixture: ComponentFixture<Report152Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report152Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report152Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
