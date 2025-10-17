import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report97Component } from './report97.component';

describe('Report97Component', () => {
  let component: Report97Component;
  let fixture: ComponentFixture<Report97Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report97Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report97Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
