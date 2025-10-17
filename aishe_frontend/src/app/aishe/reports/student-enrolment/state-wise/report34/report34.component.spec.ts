import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report34Component } from './report34.component';

describe('Report34Component', () => {
  let component: Report34Component;
  let fixture: ComponentFixture<Report34Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report34Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report34Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
