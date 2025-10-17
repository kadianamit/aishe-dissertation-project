import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report142Component } from './report142.component';

describe('Report142Component', () => {
  let component: Report142Component;
  let fixture: ComponentFixture<Report142Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report142Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report142Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
