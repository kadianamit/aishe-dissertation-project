import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KnowYourInstitutionsComponent } from './know-your-institutions.component';

describe('KnowYourInstitutionsComponent', () => {
  let component: KnowYourInstitutionsComponent;
  let fixture: ComponentFixture<KnowYourInstitutionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KnowYourInstitutionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KnowYourInstitutionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
