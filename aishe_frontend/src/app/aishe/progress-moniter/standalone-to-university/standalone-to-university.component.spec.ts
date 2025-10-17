import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandaloneToUniversityComponent } from './standalone-to-university.component';

describe('StandaloneToUniversityComponent', () => {
  let component: StandaloneToUniversityComponent;
  let fixture: ComponentFixture<StandaloneToUniversityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StandaloneToUniversityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StandaloneToUniversityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
