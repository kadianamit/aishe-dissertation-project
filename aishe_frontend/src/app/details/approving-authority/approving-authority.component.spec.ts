import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovingAuthorityComponent } from './approving-authority.component';

describe('ApprovingAuthorityComponent', () => {
  let component: ApprovingAuthorityComponent;
  let fixture: ComponentFixture<ApprovingAuthorityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovingAuthorityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovingAuthorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
