import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstitutionContactDetailsComponent } from './institution-contact-details.component';

describe('InstitutionContactDetailsComponent', () => {
  let component: InstitutionContactDetailsComponent;
  let fixture: ComponentFixture<InstitutionContactDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstitutionContactDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstitutionContactDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
