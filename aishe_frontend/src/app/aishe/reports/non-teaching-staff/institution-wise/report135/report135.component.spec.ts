import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report135Component } from './report135.component';

describe('Report135Component', () => {
  let component: Report135Component;
  let fixture: ComponentFixture<Report135Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report135Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report135Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
