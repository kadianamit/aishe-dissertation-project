import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report145Component } from './report145.component';

describe('Report145Component', () => {
  let component: Report145Component;
  let fixture: ComponentFixture<Report145Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report145Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report145Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
