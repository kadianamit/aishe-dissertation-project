import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InactiveDeaffilliateComponent } from './inactive-deaffilliate.component';

describe('InactiveDeaffilliateComponent', () => {
  let component: InactiveDeaffilliateComponent;
  let fixture: ComponentFixture<InactiveDeaffilliateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InactiveDeaffilliateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InactiveDeaffilliateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
