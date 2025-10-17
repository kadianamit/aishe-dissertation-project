import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckSimilarInsitituteComponent } from './check-similar-insititute.component';

describe('CheckSimilarInsitituteComponent', () => {
  let component: CheckSimilarInsitituteComponent;
  let fixture: ComponentFixture<CheckSimilarInsitituteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckSimilarInsitituteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckSimilarInsitituteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
