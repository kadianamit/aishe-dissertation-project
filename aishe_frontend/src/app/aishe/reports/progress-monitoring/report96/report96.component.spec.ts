import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report96Component } from './report96.component';

describe('Report96Component', () => {
  let component: Report96Component;
  let fixture: ComponentFixture<Report96Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report96Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report96Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
