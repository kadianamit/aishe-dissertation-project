import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report26Component } from './report26.component';

describe('Report26Component', () => {
  let component: Report26Component;
  let fixture: ComponentFixture<Report26Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report26Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report26Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
