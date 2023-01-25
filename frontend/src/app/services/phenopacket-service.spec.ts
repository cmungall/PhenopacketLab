import { TestBed, inject } from '@angular/core/testing';
import { PhenopacketService } from './phenopacket.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment';

const lateralitiesData = require('../../assets/data/laterality.json');
const modifiersData = require('../../assets/data/modifiers.json');
const severitiesData = require('../../assets/data/severity.json');
const sexesData = require('../../assets/data/sex.json');
const gendersData = require('../../assets/data/gender.json');

describe('PhenopacketService', () => {

    let httpMock: HttpTestingController;
    let phenopacketService: PhenopacketService;


    beforeEach(() => {

        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [PhenopacketService]
        });

        phenopacketService = TestBed.get(PhenopacketService);
        httpMock = TestBed.get(HttpTestingController);

    });


    it('should be created', inject([PhenopacketService], (service: PhenopacketService) => {
        expect(service).toBeTruthy();
    }));

    it('getLaterality() should http GET lateralities', () => {
        phenopacketService.getLaterality().subscribe((lateralities) => {
            expect(lateralities).toBe(lateralitiesData);
        });
        const req = httpMock.expectOne(environment.LATERALITY_URL);
        expect(req.request.method).toEqual('GET');
        req.flush(lateralitiesData);
        httpMock.verify();
    });

    it('getModifiers() should http GET Modifiers', () => {
        phenopacketService.getModifiers().subscribe((modifiers) => {
            expect(modifiers).toBe(modifiersData);
        });
        const req = httpMock.expectOne(environment.MODIFIERS_URL);
        expect(req.request.method).toEqual('GET');
        req.flush(modifiersData);
        httpMock.verify();
    });

    it('getSeverity() should http GET severities', () => {
        phenopacketService.getSeverity().subscribe((severities) => {
            expect(severities).toBe(severitiesData);
        });
        const req = httpMock.expectOne(environment.SEVERITY_URL);
        expect(req.request.method).toEqual('GET');
        req.flush(severitiesData);
        httpMock.verify();
    });

    it('getSex() should http GET sexes', () => {
        phenopacketService.getSex().subscribe((sexes) => {
            expect(sexes).toBe(sexesData);
        });
        const req = httpMock.expectOne(environment.SEX_URL);
        expect(req.request.method).toEqual('GET');
        req.flush(sexesData);
        httpMock.verify();
    });

    it('getGender() should http GET genders', () => {
        phenopacketService.getGender().subscribe((genders) => {
            expect(genders).toBe(gendersData);
        });
        const req = httpMock.expectOne(environment.GENDER_URL);
        expect(req.request.method).toEqual('GET');
        req.flush(gendersData);
        httpMock.verify();
    });

    afterAll(() => {
        TestBed.resetTestingModule();
    });

    afterEach(() => {
        TestBed.resetTestingModule();
    });
});
