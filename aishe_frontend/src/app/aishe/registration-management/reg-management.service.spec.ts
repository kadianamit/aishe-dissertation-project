import { TestBed } from '@angular/core/testing';

import { RegManagementService } from './reg-management.service';

describe('RegManagementService', () => {
  let service: RegManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
