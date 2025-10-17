import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitcellDialogComponent } from './unitcell-dialog.component';

describe('UnitcellDialogComponent', () => {
  let component: UnitcellDialogComponent;
  let fixture: ComponentFixture<UnitcellDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnitcellDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitcellDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
