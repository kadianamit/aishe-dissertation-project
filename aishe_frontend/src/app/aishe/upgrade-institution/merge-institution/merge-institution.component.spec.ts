import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MergeInstitutionComponent } from './merge-institution.component';

describe('MergeInstitutionComponent', () => {
  let component: MergeInstitutionComponent;
  let fixture: ComponentFixture<MergeInstitutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MergeInstitutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MergeInstitutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
