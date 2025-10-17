import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report165Component } from './report165.component';

describe('Report165Component', () => {
  let component: Report165Component;
  let fixture: ComponentFixture<Report165Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report165Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report165Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
