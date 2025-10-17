import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report156Component } from './report156.component';

describe('Report156Component', () => {
  let component: Report156Component;
  let fixture: ComponentFixture<Report156Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report156Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report156Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
