import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report105bComponent } from './report105b.component';

describe('Report105bComponent', () => {
  let component: Report105bComponent;
  let fixture: ComponentFixture<Report105bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report105bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report105bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
