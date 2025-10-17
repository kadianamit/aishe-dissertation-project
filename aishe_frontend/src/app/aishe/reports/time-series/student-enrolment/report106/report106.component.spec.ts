import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report106Component } from './report106.component';

describe('Report106Component', () => {
  let component: Report106Component;
  let fixture: ComponentFixture<Report106Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report106Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report106Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
