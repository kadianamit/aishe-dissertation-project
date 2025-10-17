import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailSmsManagementComponent } from './email-sms-management.component';

describe('EmailSmsManagementComponent', () => {
  let component: EmailSmsManagementComponent;
  let fixture: ComponentFixture<EmailSmsManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmailSmsManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailSmsManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
