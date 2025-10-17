import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackListSnoComponent } from './feedback-list-sno.component';

describe('FeedbackListSnoComponent', () => {
  let component: FeedbackListSnoComponent;
  let fixture: ComponentFixture<FeedbackListSnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackListSnoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackListSnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
