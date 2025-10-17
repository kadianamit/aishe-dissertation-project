import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report124cComponent } from './report124c.component';

describe('Report124cComponent', () => {
  let component: Report124cComponent;
  let fixture: ComponentFixture<Report124cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report124cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report124cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
