import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report166Component } from './report166.component';

describe('Report166Component', () => {
  let component: Report166Component;
  let fixture: ComponentFixture<Report166Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report166Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report166Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
