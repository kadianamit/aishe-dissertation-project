import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutSurveyComponent } from './about-survey.component';

describe('AboutSurveyComponent', () => {
  let component: AboutSurveyComponent;
  let fixture: ComponentFixture<AboutSurveyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AboutSurveyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
