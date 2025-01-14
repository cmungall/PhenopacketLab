import { TestBed, inject } from '@angular/core/testing';
import { PhenopacketService } from './phenopacket.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DialogService } from 'primeng/dynamicdialog';
import { DownloadService } from './download-service';
import { MessageService } from 'primeng/api';


describe('PhenopacketService', () => {

    let httpMock: HttpTestingController;
    let phenopacketService: PhenopacketService;
    let dialogService: DialogService;

    beforeEach(() => {

        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [
                PhenopacketService,
                DownloadService,
                MessageService,
                DialogService
            ]
        });

        phenopacketService = TestBed.get(PhenopacketService);
        dialogService = TestBed.get(DialogService);
        httpMock = TestBed.get(HttpTestingController);

    });


    it('should be created', inject([PhenopacketService], (service: PhenopacketService) => {
        expect(service).toBeTruthy();
    }));

    afterAll(() => {
        TestBed.resetTestingModule();
    });

    afterEach(() => {
        TestBed.resetTestingModule();
    });
});
