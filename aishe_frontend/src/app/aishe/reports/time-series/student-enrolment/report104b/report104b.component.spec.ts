import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report104bComponent } from './report104b.component';

describe('Report104bComponent', () => {
  let component: Report104bComponent;
  let fixture: ComponentFixture<Report104bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report104bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report104bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
