import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report104Component } from './report104.component';

describe('Report104Component', () => {
  let component: Report104Component;
  let fixture: ComponentFixture<Report104Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report104Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report104Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
