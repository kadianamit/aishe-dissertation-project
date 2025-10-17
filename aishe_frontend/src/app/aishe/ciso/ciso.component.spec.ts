import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CisoComponent } from './ciso.component';

describe('CisoComponent', () => {
  let component: CisoComponent;
  let fixture: ComponentFixture<CisoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CisoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CisoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
