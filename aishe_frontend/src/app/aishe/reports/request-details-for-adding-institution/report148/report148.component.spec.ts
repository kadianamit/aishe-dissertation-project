import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report148Component } from './report148.component';

describe('Report148Component', () => {
  let component: Report148Component;
  let fixture: ComponentFixture<Report148Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report148Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report148Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
