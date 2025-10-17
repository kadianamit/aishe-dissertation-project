import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report17Component } from './report17.component';

describe('Report17Component', () => {
  let component: Report17Component;
  let fixture: ComponentFixture<Report17Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report17Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report17Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
