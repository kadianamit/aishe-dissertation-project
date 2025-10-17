import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report167Component } from './report167.component';

describe('Report167Component', () => {
  let component: Report167Component;
  let fixture: ComponentFixture<Report167Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report167Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report167Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
