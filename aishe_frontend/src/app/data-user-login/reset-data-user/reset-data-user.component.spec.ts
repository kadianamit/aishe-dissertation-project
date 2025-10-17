import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetDataUserComponent } from './reset-data-user.component';

describe('ResetDataUserComponent', () => {
  let component: ResetDataUserComponent;
  let fixture: ComponentFixture<ResetDataUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResetDataUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetDataUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
