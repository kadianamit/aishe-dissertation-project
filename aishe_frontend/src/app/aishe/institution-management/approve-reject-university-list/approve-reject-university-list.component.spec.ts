import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveRejectUniversityListComponent } from './approve-reject-university-list.component';

describe('ApproveRejectUniversityListComponent', () => {
  let component: ApproveRejectUniversityListComponent;
  let fixture: ComponentFixture<ApproveRejectUniversityListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveRejectUniversityListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveRejectUniversityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
