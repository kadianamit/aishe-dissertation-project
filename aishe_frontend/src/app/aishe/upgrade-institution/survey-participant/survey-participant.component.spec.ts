import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyParticipantComponent } from './survey-participant.component';

describe('SurveyParticipantComponent', () => {
  let component: SurveyParticipantComponent;
  let fixture: ComponentFixture<SurveyParticipantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyParticipantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SurveyParticipantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
