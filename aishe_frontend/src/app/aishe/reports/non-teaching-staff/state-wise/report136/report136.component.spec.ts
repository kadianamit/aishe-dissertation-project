import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report136Component } from './report136.component';

describe('Report136Component', () => {
  let component: Report136Component;
  let fixture: ComponentFixture<Report136Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report136Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report136Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
