import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report100bComponent } from './report100b.component';

describe('Report100bComponent', () => {
  let component: Report100bComponent;
  let fixture: ComponentFixture<Report100bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report100bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report100bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
