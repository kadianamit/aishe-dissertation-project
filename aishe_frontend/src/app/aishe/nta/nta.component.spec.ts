import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NtaComponent } from './nta.component';

describe('NtaComponent', () => {
  let component: NtaComponent;
  let fixture: ComponentFixture<NtaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NtaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NtaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
