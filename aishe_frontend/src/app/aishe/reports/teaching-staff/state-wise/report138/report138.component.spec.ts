import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report138Component } from './report138.component';

describe('Report138Component', () => {
  let component: Report138Component;
  let fixture: ComponentFixture<Report138Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report138Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report138Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
