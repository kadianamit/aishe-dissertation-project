import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveForeignInstitutionComponent } from './approve-foreign-institution.component';

describe('ApproveForeignInstitutionComponent', () => {
  let component: ApproveForeignInstitutionComponent;
  let fixture: ComponentFixture<ApproveForeignInstitutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveForeignInstitutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveForeignInstitutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
