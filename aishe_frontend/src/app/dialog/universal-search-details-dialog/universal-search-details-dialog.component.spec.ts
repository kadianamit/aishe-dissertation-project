import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversalSearchDetailsDialogComponent } from './universal-search-details-dialog.component';

describe('UniversalSearchDetailsDialogComponent', () => {
  let component: UniversalSearchDetailsDialogComponent;
  let fixture: ComponentFixture<UniversalSearchDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversalSearchDetailsDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversalSearchDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
