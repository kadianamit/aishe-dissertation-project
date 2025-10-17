import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddResearchAndDevelopmentComponent } from './add-research-and-development.component';

describe('AddResearchAndDevelopmentComponent', () => {
  let component: AddResearchAndDevelopmentComponent;
  let fixture: ComponentFixture<AddResearchAndDevelopmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddResearchAndDevelopmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddResearchAndDevelopmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
