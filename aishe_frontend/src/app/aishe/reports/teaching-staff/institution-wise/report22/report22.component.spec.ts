import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report22Component } from './report22.component';

describe('Report22Component', () => {
  let component: Report22Component;
  let fixture: ComponentFixture<Report22Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report22Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report22Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
