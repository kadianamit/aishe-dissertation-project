import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report12Component } from './report12.component';

describe('Report12Component', () => {
  let component: Report12Component;
  let fixture: ComponentFixture<Report12Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report12Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report12Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
