import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report25Component } from './report25.component';

describe('Report25Component', () => {
  let component: Report25Component;
  let fixture: ComponentFixture<Report25Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report25Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report25Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
