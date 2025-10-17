import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report154Component } from './report154.component';

describe('Report154Component', () => {
  let component: Report154Component;
  let fixture: ComponentFixture<Report154Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report154Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report154Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
