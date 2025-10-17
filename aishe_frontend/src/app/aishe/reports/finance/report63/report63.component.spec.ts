import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report63Component } from './report63.component';

describe('Report63Component', () => {
  let component: Report63Component;
  let fixture: ComponentFixture<Report63Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report63Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report63Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
