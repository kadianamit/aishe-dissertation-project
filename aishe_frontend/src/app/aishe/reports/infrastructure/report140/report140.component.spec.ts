import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report140Component } from './report140.component';

describe('Report140Component', () => {
  let component: Report140Component;
  let fixture: ComponentFixture<Report140Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report140Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report140Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
