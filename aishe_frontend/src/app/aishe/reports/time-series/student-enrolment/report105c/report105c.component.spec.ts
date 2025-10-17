import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report105cComponent } from './report105c.component';

describe('Report105cComponent', () => {
  let component: Report105cComponent;
  let fixture: ComponentFixture<Report105cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report105cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report105cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
