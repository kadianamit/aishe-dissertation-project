import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report149Component } from './report149.component';

describe('Report149Component', () => {
  let component: Report149Component;
  let fixture: ComponentFixture<Report149Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report149Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report149Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
