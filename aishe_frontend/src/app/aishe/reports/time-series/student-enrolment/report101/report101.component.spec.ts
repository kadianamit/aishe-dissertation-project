import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report101Component } from './report101.component';

describe('Report101Component', () => {
  let component: Report101Component;
  let fixture: ComponentFixture<Report101Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report101Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report101Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
