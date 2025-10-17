import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report59Component } from './report59.component';

describe('Report59Component', () => {
  let component: Report59Component;
  let fixture: ComponentFixture<Report59Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report59Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report59Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
