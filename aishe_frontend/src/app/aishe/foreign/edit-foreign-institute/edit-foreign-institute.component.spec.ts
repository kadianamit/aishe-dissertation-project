import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForeignInstituteComponent } from './edit-foreign-institute.component';

describe('EditForeignInstituteComponent', () => {
  let component: EditForeignInstituteComponent;
  let fixture: ComponentFixture<EditForeignInstituteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditForeignInstituteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForeignInstituteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
