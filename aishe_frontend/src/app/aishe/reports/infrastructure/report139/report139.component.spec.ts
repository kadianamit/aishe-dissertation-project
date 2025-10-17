import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report139Component } from './report139.component';

describe('Report139Component', () => {
  let component: Report139Component;
  let fixture: ComponentFixture<Report139Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report139Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report139Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
