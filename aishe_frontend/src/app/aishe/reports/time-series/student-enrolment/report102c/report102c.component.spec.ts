import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report102cComponent } from './report102c.component';

describe('Report102cComponent', () => {
  let component: Report102cComponent;
  let fixture: ComponentFixture<Report102cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report102cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report102cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
