import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestDetailsForAddingInstitutionComponent } from './request-details-for-adding-institution.component';

describe('RequestDetailsForAddingInstitutionComponent', () => {
  let component: RequestDetailsForAddingInstitutionComponent;
  let fixture: ComponentFixture<RequestDetailsForAddingInstitutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestDetailsForAddingInstitutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestDetailsForAddingInstitutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
