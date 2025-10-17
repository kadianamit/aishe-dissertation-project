import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandaloneToCollegeComponent } from './standalone-to-college.component';

describe('StandaloneToCollegeComponent', () => {
  let component: StandaloneToCollegeComponent;
  let fixture: ComponentFixture<StandaloneToCollegeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StandaloneToCollegeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StandaloneToCollegeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
