import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityWiseCollegeComponent } from './university-wise-college.component';

describe('UniversityWiseCollegeComponent', () => {
  let component: UniversityWiseCollegeComponent;
  let fixture: ComponentFixture<UniversityWiseCollegeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversityWiseCollegeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversityWiseCollegeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
