import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report171Component } from './report171.component';

describe('Report171Component', () => {
  let component: Report171Component;
  let fixture: ComponentFixture<Report171Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report171Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report171Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
