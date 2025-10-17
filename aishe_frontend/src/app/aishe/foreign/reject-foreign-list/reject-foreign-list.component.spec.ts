import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectForeignListComponent } from './reject-foreign-list.component';

describe('RejectForeignListComponent', () => {
  let component: RejectForeignListComponent;
  let fixture: ComponentFixture<RejectForeignListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejectForeignListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RejectForeignListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
