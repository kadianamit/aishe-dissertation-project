import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemunerationStatusDialogComponent } from './remuneration-status-dialog.component';

describe('RemunerationStatusDialogComponent', () => {
  let component: RemunerationStatusDialogComponent;
  let fixture: ComponentFixture<RemunerationStatusDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RemunerationStatusDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RemunerationStatusDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
