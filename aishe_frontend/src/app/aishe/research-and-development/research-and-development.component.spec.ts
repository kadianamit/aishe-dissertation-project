import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResearchAndDevelopmentComponent } from './research-and-development.component';

describe('ResearchAndDevelopmentComponent', () => {
  let component: ResearchAndDevelopmentComponent;
  let fixture: ComponentFixture<ResearchAndDevelopmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResearchAndDevelopmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResearchAndDevelopmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
