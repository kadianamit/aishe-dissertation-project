import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report105aComponent } from './report105a.component';

describe('Report105aComponent', () => {
  let component: Report105aComponent;
  let fixture: ComponentFixture<Report105aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report105aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report105aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
