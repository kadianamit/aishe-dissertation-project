import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignInstituteDialogComponent } from './foreign-institute-dialog.component';

describe('ForeignInstituteDialogComponent', () => {
  let component: ForeignInstituteDialogComponent;
  let fixture: ComponentFixture<ForeignInstituteDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForeignInstituteDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForeignInstituteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
