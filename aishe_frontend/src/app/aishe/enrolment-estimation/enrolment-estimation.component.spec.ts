import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolmentEstimationComponent } from './enrolment-estimation.component';

describe('EnrolmentEstimationComponent', () => {
  let component: EnrolmentEstimationComponent;
  let fixture: ComponentFixture<EnrolmentEstimationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrolmentEstimationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrolmentEstimationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
