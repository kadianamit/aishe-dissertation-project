import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoeManagementComponent } from './moe-management.component';

describe('MoeManagementComponent', () => {
  let component: MoeManagementComponent;
  let fixture: ComponentFixture<MoeManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MoeManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MoeManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
