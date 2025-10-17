import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report162Component } from './report162.component';

describe('Report162Component', () => {
  let component: Report162Component;
  let fixture: ComponentFixture<Report162Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report162Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report162Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
