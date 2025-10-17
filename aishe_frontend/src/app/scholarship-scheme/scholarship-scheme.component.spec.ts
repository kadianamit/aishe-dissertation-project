import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScholarshipSchemeComponent } from './scholarship-scheme.component';

describe('ScholarshipSchemeComponent', () => {
  let component: ScholarshipSchemeComponent;
  let fixture: ComponentFixture<ScholarshipSchemeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScholarshipSchemeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScholarshipSchemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
