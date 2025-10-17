import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyLogComponent } from './survey-log.component';

describe('SurveyLogComponent', () => {
  let component: SurveyLogComponent;
  let fixture: ComponentFixture<SurveyLogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyLogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SurveyLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
