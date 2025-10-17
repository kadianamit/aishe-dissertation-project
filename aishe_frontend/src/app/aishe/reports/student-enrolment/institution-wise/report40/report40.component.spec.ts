import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report40Component } from './report40.component';

describe('Report40Component', () => {
  let component: Report40Component;
  let fixture: ComponentFixture<Report40Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report40Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report40Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
