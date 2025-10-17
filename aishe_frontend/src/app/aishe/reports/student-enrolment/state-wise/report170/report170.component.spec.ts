import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report170Component } from './report170.component';

describe('Report170Component', () => {
  let component: Report170Component;
  let fixture: ComponentFixture<Report170Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report170Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report170Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
