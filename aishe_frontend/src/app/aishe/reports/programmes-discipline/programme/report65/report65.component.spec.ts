import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report65Component } from './report65.component';

describe('Report65Component', () => {
  let component: Report65Component;
  let fixture: ComponentFixture<Report65Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report65Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report65Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
