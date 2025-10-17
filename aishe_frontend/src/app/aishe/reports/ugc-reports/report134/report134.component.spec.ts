import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report134Component } from './report134.component';

describe('Report134Component', () => {
  let component: Report134Component;
  let fixture: ComponentFixture<Report134Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report134Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report134Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
