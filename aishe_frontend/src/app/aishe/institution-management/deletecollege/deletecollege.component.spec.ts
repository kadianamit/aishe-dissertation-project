import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletecollegeComponent } from './deletecollege.component';

describe('DeletecollegeComponent', () => {
  let component: DeletecollegeComponent;
  let fixture: ComponentFixture<DeletecollegeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletecollegeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletecollegeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
