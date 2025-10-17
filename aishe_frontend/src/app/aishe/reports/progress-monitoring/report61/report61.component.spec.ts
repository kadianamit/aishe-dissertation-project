import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report61Component } from './report61.component';

describe('Report61Component', () => {
  let component: Report61Component;
  let fixture: ComponentFixture<Report61Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report61Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report61Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
