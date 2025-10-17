import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailUnitCellComponent } from './detail-unit-cell.component';

describe('DetailUnitCellComponent', () => {
  let component: DetailUnitCellComponent;
  let fixture: ComponentFixture<DetailUnitCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailUnitCellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailUnitCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
