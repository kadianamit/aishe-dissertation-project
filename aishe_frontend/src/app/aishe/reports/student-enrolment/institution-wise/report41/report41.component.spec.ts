import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report41Component } from './report41.component';

describe('Report41Component', () => {
  let component: Report41Component;
  let fixture: ComponentFixture<Report41Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report41Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report41Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
