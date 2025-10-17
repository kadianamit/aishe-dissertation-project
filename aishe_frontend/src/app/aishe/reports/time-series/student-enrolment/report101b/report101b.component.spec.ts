import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report101bComponent } from './report101b.component';

describe('Report101bComponent', () => {
  let component: Report101bComponent;
  let fixture: ComponentFixture<Report101bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report101bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report101bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
