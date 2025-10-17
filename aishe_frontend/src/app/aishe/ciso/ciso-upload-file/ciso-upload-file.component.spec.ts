import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CisoUploadFileComponent } from './ciso-upload-file.component';

describe('CisoUploadFileComponent', () => {
  let component: CisoUploadFileComponent;
  let fixture: ComponentFixture<CisoUploadFileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CisoUploadFileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CisoUploadFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
