import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstitutionDirectoryComponent } from './institution-directory.component';

describe('InstitutionDirectoryComponent', () => {
  let component: InstitutionDirectoryComponent;
  let fixture: ComponentFixture<InstitutionDirectoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstitutionDirectoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstitutionDirectoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
