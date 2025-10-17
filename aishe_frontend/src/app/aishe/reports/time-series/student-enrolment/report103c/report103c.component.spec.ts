import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report103cComponent } from './report103c.component';

describe('Report103cComponent', () => {
  let component: Report103cComponent;
  let fixture: ComponentFixture<Report103cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report103cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report103cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
