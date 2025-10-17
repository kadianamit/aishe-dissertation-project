import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResearchAndDevelopmentRemarksDialogComponent } from './research-and-development-remarks-dialog.component';

describe('ResearchAndDevelopmentRemarksDialogComponent', () => {
  let component: ResearchAndDevelopmentRemarksDialogComponent;
  let fixture: ComponentFixture<ResearchAndDevelopmentRemarksDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResearchAndDevelopmentRemarksDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResearchAndDevelopmentRemarksDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
