import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditRADComponent } from './edit-rad.component';

describe('EditRADComponent', () => {
  let component: EditRADComponent;
  let fixture: ComponentFixture<EditRADComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditRADComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditRADComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
