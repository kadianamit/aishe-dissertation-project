import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report144Component } from './report144.component';

describe('Report144Component', () => {
  let component: Report144Component;
  let fixture: ComponentFixture<Report144Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report144Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report144Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
