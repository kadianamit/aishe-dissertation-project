import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report132Component } from './report132.component';

describe('Report132Component', () => {
  let component: Report132Component;
  let fixture: ComponentFixture<Report132Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report132Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report132Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
