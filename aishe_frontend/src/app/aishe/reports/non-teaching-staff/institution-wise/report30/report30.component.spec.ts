import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report30Component } from './report30.component';

describe('Report30Component', () => {
  let component: Report30Component;
  let fixture: ComponentFixture<Report30Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report30Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report30Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
