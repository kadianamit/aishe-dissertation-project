import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report128Component } from './report128.component';

describe('Report128Component', () => {
  let component: Report128Component;
  let fixture: ComponentFixture<Report128Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report128Component ]
    })
    .compileComponents();
  });


  beforeEach(() => {
    fixture = TestBed.createComponent(Report128Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
