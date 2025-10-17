import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report118Component } from './report118.component';

describe('Report118Component', () => {
  let component: Report118Component;
  let fixture: ComponentFixture<Report118Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report118Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report118Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
