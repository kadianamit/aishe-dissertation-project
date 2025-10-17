import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeInstitutionComponent } from './upgrade-institution.component';

describe('UpgradeInstitutionComponent', () => {
  let component: UpgradeInstitutionComponent;
  let fixture: ComponentFixture<UpgradeInstitutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpgradeInstitutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradeInstitutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
