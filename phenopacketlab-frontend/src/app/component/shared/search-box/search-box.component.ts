import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { DiseaseSearchService } from 'src/app/services/disease-search.service';
import { PhenotypeSearchService } from 'src/app/services/phenotype-search.service';
import { ConstantObject } from 'src/app/models/individual';
import { MedicalActionService } from 'src/app/services/medical-action.service';


@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrls: ['./search-box.component.scss']
})
export class SearchBoxComponent implements OnInit {

    @Input()
    searchLabel: string;
    @Input()
    itemName: string;
    // can be 'feature' or 'disease'
    @Input()
    searchType: string;
    @Input()
    showHint;
    @Output()
    selectedItemChange = new EventEmitter<any>();

    @Input()
    placeHolderTxt;

    selectable = true;
    removable = true;

    items: ConstantObject[];
    groupedItems: any[];
    selectedItem: any;
    itemsCount: number;

    searchstate = 'inactive';
    query = new Subject();
    navFilter = 'feature';
    queryString = '';
    queryText: string;
    notFoundFlag = false;
    loadingSearchResults = false;

    constructor(private phenotypicSearchService: PhenotypeSearchService,
        private diseaseSearchService: DiseaseSearchService,
        private medicalActionService: MedicalActionService) { }

    ngOnInit() {
        this.query.pipe(debounceTime(425),
            distinctUntilChanged()).subscribe((val: string) => {
                if (this.hasValidInput(val)) {
                    this.loadingSearchResults = true;
                    this.queryText = val;
                    if (this.searchType === 'feature') {
                        this.phenotypicSearchService.searchPhenotypicFeatures(val).subscribe((data) => {
                            this.setItems(data);
                        }, (error) => {
                            console.log(error);
                            this.loadingSearchResults = false;
                        }, () => {
                            this.loadingSearchResults = false;
                        });
                    }
                    if (this.searchType === 'disease') {
                        this.diseaseSearchService.searchDiseases(val).subscribe((data) => {
                            this.setItems(data);
                        }, (error) => {
                            console.log(error);
                            this.loadingSearchResults = false;
                        }, () => {
                            this.loadingSearchResults = false;
                        });
                    }
                    if (this.searchType === 'chebi') {
                        this.medicalActionService.searchChemicalEntities(val).subscribe((data) => {
                            this.setItems(data);
                        }, (error) => {
                            console.log(error);
                            this.loadingSearchResults = false;
                        }, () => {
                            this.loadingSearchResults = false;
                        });
                    }

                } else {
                    this.searchstate = 'inactive';
                }
            }); // End debounce subscribe
    }

    setItems(data: any) {
        this.items = [];
        for (const concept of data.foundConcepts) {
            this.items.push(new ConstantObject(concept.lbl, concept.def, concept.id, concept.syn));
        }
        this.itemsCount = data.numberOfTerms;
        this.groupedItems = [{ label: `${this.items.length} of ${this.itemsCount} displayed`, items: this.items }];

        this.notFoundFlag = (this.itemsCount === 0);
        this.searchstate = 'active';
    }

    contentChanging(input: string) {
        this.query.next(input);
    }

    hasValidInput(qString: string) {
        return (qString && qString.length >= 3);
    }

    // Submit query to search results page
    submitQuery(input: any) {
        if (this.searchstate === 'active') {
            this.searchstate = 'inactive';
        }
        if (this.searchType === 'feature') {
            this.phenotypicSearchService.getPhenotypicFeatureById(input.id).subscribe(res => {
                this.selectedItemChange.emit(res);
            });
        }
        if (this.searchType === 'disease') {
            this.diseaseSearchService.getDiseaseById(input.id).subscribe(res => {
                this.selectedItemChange.emit(res);
            });
        }
        if (this.searchType === 'chebi') {
            this.medicalActionService.getChemicalEntityById(input.id).subscribe(res => {
                this.selectedItemChange.emit(res);
            });
        }
        // clear field
        this.selectedItem = undefined;
    }
}

