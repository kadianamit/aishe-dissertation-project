import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report54Component } from './report54.component';

describe('Report54Component', () => {
  let component: Report54Component;
  let fixture: ComponentFixture<Report54Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report54Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report54Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
