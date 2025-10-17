import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeachingDemoDfcComponent } from './teaching-demo-dfc.component';

describe('TeachingDemoDfcComponent', () => {
  let component: TeachingDemoDfcComponent;
  let fixture: ComponentFixture<TeachingDemoDfcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeachingDemoDfcComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeachingDemoDfcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
