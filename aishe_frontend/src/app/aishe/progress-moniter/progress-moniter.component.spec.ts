import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgressMoniterComponent } from './progress-moniter.component';

describe('ProgressMoniterComponent', () => {
  let component: ProgressMoniterComponent;
  let fixture: ComponentFixture<ProgressMoniterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgressMoniterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgressMoniterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
