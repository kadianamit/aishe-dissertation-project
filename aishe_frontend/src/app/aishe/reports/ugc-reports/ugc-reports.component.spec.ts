import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UgcReportsComponent } from './ugc-reports.component';

describe('UgcReportsComponent', () => {
  let component: UgcReportsComponent;
  let fixture: ComponentFixture<UgcReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UgcReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UgcReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
