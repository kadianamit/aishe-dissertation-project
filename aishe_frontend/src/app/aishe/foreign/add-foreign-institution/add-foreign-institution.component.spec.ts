import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddForeignInstitutionComponent } from './add-foreign-institution.component';

describe('AddForeignInstitutionComponent', () => {
  let component: AddForeignInstitutionComponent;
  let fixture: ComponentFixture<AddForeignInstitutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddForeignInstitutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddForeignInstitutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
