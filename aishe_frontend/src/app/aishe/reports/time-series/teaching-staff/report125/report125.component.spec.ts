import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report125Component } from './report125.component';

describe('Report125Component', () => {
  let component: Report125Component;
  let fixture: ComponentFixture<Report125Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report125Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report125Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
