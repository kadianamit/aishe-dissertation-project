import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report37Component } from './report37.component';

describe('Report37Component', () => {
  let component: Report37Component;
  let fixture: ComponentFixture<Report37Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report37Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report37Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
