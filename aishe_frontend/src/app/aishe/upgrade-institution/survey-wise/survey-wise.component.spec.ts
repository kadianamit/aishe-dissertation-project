import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyWiseComponent } from './survey-wise.component';

describe('SurveyWiseComponent', () => {
  let component: SurveyWiseComponent;
  let fixture: ComponentFixture<SurveyWiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyWiseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SurveyWiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
