import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentEnrolmentComponent } from './student-enrolment.component';

describe('StudentEnrolmentComponent', () => {
  let component: StudentEnrolmentComponent;
  let fixture: ComponentFixture<StudentEnrolmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentEnrolmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentEnrolmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
