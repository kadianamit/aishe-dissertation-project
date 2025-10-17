import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradedInstitutionComponent } from './upgraded-institution.component';

describe('UpgradedInstitutionComponent', () => {
  let component: UpgradedInstitutionComponent;
  let fixture: ComponentFixture<UpgradedInstitutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpgradedInstitutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradedInstitutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
