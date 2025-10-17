import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissingUserComponent } from './missing-user.component';

describe('MissingUserComponent', () => {
  let component: MissingUserComponent;
  let fixture: ComponentFixture<MissingUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissingUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MissingUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
