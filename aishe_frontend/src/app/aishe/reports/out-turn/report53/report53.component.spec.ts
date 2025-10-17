import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report53Component } from './report53.component';

describe('Report53Component', () => {
  let component: Report53Component;
  let fixture: ComponentFixture<Report53Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report53Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report53Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
