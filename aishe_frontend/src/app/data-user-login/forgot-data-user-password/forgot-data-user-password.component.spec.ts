import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgotDataUserPasswordComponent } from './forgot-data-user-password.component';

describe('ForgotDataUserPasswordComponent', () => {
  let component: ForgotDataUserPasswordComponent;
  let fixture: ComponentFixture<ForgotDataUserPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForgotDataUserPasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForgotDataUserPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
