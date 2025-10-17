import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignDashboardComponent } from './foreign-dashboard.component';

describe('ForeignDashboardComponent', () => {
  let component: ForeignDashboardComponent;
  let fixture: ComponentFixture<ForeignDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForeignDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForeignDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
