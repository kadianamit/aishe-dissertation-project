import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report137Component } from './report137.component';

describe('Report137Component', () => {
  let component: Report137Component;
  let fixture: ComponentFixture<Report137Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report137Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report137Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
