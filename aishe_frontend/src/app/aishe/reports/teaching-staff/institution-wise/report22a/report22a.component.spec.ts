import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report22aComponent } from './report22a.component';

describe('Report22aComponent', () => {
  let component: Report22aComponent;
  let fixture: ComponentFixture<Report22aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report22aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report22aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
