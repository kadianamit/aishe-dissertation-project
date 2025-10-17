import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report143Component } from './report143.component';

describe('Report143Component', () => {
  let component: Report143Component;
  let fixture: ComponentFixture<Report143Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report143Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report143Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
