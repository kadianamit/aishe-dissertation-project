import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report113Component } from './report113.component';

describe('Report113Component', () => {
  let component: Report113Component;
  let fixture: ComponentFixture<Report113Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report113Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report113Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
