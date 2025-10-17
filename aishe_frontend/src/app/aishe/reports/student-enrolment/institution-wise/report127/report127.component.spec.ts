import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report127Component } from './report127.component';

describe('Report127Component', () => {
  let component: Report127Component;
  let fixture: ComponentFixture<Report127Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report127Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report127Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
