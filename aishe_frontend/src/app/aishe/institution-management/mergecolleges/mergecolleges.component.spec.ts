import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MergecollegesComponent } from './mergecolleges.component';

describe('MergecollegesComponent', () => {
  let component: MergecollegesComponent;
  let fixture: ComponentFixture<MergecollegesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MergecollegesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MergecollegesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
