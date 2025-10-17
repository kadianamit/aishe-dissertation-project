import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report103bComponent } from './report103b.component';

describe('Report103bComponent', () => {
  let component: Report103bComponent;
  let fixture: ComponentFixture<Report103bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report103bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report103bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
