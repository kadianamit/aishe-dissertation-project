import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report21Component } from './report21.component';

describe('Report21Component', () => {
  let component: Report21Component;
  let fixture: ComponentFixture<Report21Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report21Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report21Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
