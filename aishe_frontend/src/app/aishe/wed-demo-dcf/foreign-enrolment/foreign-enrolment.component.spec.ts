import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignEnrolmentComponent } from './foreign-enrolment.component';

describe('ForeignEnrolmentComponent', () => {
  let component: ForeignEnrolmentComponent;
  let fixture: ComponentFixture<ForeignEnrolmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForeignEnrolmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForeignEnrolmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
