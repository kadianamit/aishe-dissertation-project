import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report29Component } from './report29.component';

describe('Report29Component', () => {
  let component: Report29Component;
  let fixture: ComponentFixture<Report29Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report29Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report29Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
