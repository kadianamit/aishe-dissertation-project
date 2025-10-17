import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadedUniversityViewDialogComponent } from './uploaded-university-view-dialog.component';

describe('UploadedUniversityViewDialogComponent', () => {
  let component: UploadedUniversityViewDialogComponent;
  let fixture: ComponentFixture<UploadedUniversityViewDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadedUniversityViewDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadedUniversityViewDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
