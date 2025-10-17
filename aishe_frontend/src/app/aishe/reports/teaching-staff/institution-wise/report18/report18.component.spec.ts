import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report18Component } from './report18.component';

describe('Report18Component', () => {
  let component: Report18Component;
  let fixture: ComponentFixture<Report18Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report18Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report18Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
