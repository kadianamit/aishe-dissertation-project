import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report103aComponent } from './report103a.component';

describe('Report103aComponent', () => {
  let component: Report103aComponent;
  let fixture: ComponentFixture<Report103aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report103aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report103aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
