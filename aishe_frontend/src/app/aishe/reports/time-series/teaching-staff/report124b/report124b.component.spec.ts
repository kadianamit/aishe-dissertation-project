import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report124bComponent } from './report124b.component';

describe('Report124bComponent', () => {
  let component: Report124bComponent;
  let fixture: ComponentFixture<Report124bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report124bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report124bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
