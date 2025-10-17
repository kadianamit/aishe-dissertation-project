import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollegeToUniversityComponent } from './college-to-university.component';

describe('CollegeToUniversityComponent', () => {
  let component: CollegeToUniversityComponent;
  let fixture: ComponentFixture<CollegeToUniversityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollegeToUniversityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CollegeToUniversityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
