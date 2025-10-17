import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityWiseOffcampusComponent } from './university-wise-offcampus.component';

describe('UniversityWiseOffcampusComponent', () => {
  let component: UniversityWiseOffcampusComponent;
  let fixture: ComponentFixture<UniversityWiseOffcampusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversityWiseOffcampusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversityWiseOffcampusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
