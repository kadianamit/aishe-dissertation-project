import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatasharingComponent } from './datasharing.component';

describe('DatasharingComponent', () => {
  let component: DatasharingComponent;
  let fixture: ComponentFixture<DatasharingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatasharingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DatasharingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
