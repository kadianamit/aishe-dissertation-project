import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report161Component } from './report161.component';

describe('Report161Component', () => {
  let component: Report161Component;
  let fixture: ComponentFixture<Report161Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report161Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report161Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
