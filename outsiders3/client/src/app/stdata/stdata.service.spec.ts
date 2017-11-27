import { TestBed, inject } from '@angular/core/testing';

import { StdataService } from './stdata.service';

describe('StdataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StdataService]
    });
  });

  it('should be created', inject([StdataService], (service: StdataService) => {
    expect(service).toBeTruthy();
  }));
});
