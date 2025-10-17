import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report133Component } from './report133.component';

describe('Report133Component', () => {
  let component: Report133Component;
  let fixture: ComponentFixture<Report133Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report133Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report133Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
