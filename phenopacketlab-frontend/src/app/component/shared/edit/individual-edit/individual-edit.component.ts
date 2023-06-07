import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { OntologyClass } from 'src/app/models/base';
import { ConstantObject, Individual, KaryotypicSex, Status } from 'src/app/models/individual';
import { ProfileSelection } from 'src/app/models/profile';
import { DiseaseSearchService } from 'src/app/services/disease-search.service';
import { PhenopacketService } from 'src/app/services/phenopacket.service';

@Component({
    selector: 'app-individual-edit',
    templateUrl: './individual-edit.component.html',
    styleUrls: ['./individual-edit.component.scss']
})
export class IndividualEditComponent implements OnInit, OnDestroy {

    @Input()
    subject: Individual;
    @Input()
    profile: ProfileSelection;
    @Output()
    subjectChange = new EventEmitter<Individual>();
    @Output()
    isPrivateInfoWarnSelectedChanged = new EventEmitter<boolean>();

    @Input()
    submitted: boolean;

    isPrivateInfoWarnSelected: boolean;
    causeOfDeaths: any[];
    selectedCauseOfDeath: any;
    causeOfDeathSubscription: Subscription;

    selectedSex: ConstantObject;
    sexes: ConstantObject[];
    sexSubscription: Subscription;

    selectedKaryotypicSex: KaryotypicSex;

    showGender = false;
    // genders: ConstantObject[];
    // selectedGender: ConstantObject;
    // genderSubscription: Subscription;

    constructor(public phenopacketService: PhenopacketService, public diseaseService: DiseaseSearchService) {
    }

    ngOnInit() {
        // get cause of death
        this.causeOfDeathSubscription = this.diseaseService.getAll().subscribe(diseases => {
            this.causeOfDeaths = diseases;
            // init selectedCauseOfDeath
            if (this.subject.vitalStatus?.causeOfDeath) {
                for (const cause of diseases) {
                    if (cause.id.value === this.subject.vitalStatus?.causeOfDeath.id) {
                        this.selectedCauseOfDeath = cause;
                        break;
                    }
                }
            }
        });
        this.sexSubscription = this.phenopacketService.getSex().subscribe(sexes => {
            this.sexes = sexes;
            for (const sex of sexes) {
                if (this.subject && this.subject.sex === sex.lbl) {
                    this.selectedSex = sex;
                }
            }
        });
        // if edit dialog then we assume that the isPrivateInfoWarnSelected has already been selected
        if (this.profile === undefined) {
            this.isPrivateInfoWarnSelected = true;
        }
        // this.genderSubscription = this.phenopacketService.getGender().subscribe(genders => {
        //     this.genders = genders;
        //     console.log(genders);
        //     for (const gender of genders) {
        //         if (this.subject && this.subject.gender?.label === gender.name) {
        //            this.selectedGender = gender;
        //         }
        //     }
        // });
        if (this.subject) {
            for (const karyosex of KaryotypicSex.VALUES) {
                if (this.subject.karyotypicSex === karyosex.name) {
                    this.selectedKaryotypicSex = karyosex;
                }
            }
        }
    }
    ngOnDestroy(): void {
        if (this.causeOfDeathSubscription) {
            this.causeOfDeathSubscription.unsubscribe();
        }
        if (this.sexSubscription) {
            this.sexSubscription.unsubscribe();
        }
        // if (this.genderSubscription) {
        //     this.genderSubscription.unsubscribe();
        // }
    }

    /**
      *
      * @param timeOfLastEncounter a TimeElement
      */
    updateTimeOfLastEncounter(timeOfLastEncounter: any) {
        if (this.subject) {
            this.subject.timeAtLastEncounter = timeOfLastEncounter;
            this.subjectChange.emit(this.subject);
        }
    }

    updateIsPrivateInfoWarnSelected() {
        this.isPrivateInfoWarnSelectedChanged.emit(this.isPrivateInfoWarnSelected);
    }

    updateSex(event: any) {
        if (this.subject) {
            if (event.value) {
                this.subject.sex = event.value.lbl;
            }
            if (event.value === undefined || event.value === null) {
                this.subject.sex = undefined;
            }
            this.subjectChange.emit(this.subject);
        }
    }

    updateKaryotypicSex(karyotypicSex: KaryotypicSex) {
        if (this.subject) {
            if (karyotypicSex) {
                this.subject.karyotypicSex = karyotypicSex.name;
            } else {
                this.subject.karyotypicSex = undefined;
            }
            this.subjectChange.emit(this.subject);
        }
    }

    // handleClickMore(event) {
    //     this.showGender = !this.showGender;
    // }

    // updateGender(gender: any) {
    //     if (this.subject) {
    //         if (gender) {
    //             this.subject.gender = new OntologyClass(gender.id.value, gender.name);
    //         } else {
    //             this.subject.gender = undefined;
    //         }
    //         this.subjectChange.emit(this.subject);
    //     }
    // }

    updateStatus(status: any) {
        if (this.subject) {
            this.subject.vitalStatus.status = status;
            this.subjectChange.emit(this.subject);
        }
    }
    updateSurvivalTime(event: any) {
        if (this.subject) {
            this.subject.vitalStatus.survivalTimeInDays = event.value;
            this.subjectChange.emit(this.subject);
        }
    }
    /**
     *
     * @param timeOfDeath a TimeElement
     */
    updateTimeOfDeath(timeOfDeath: any) {
        if (this.subject) {
            this.subject.vitalStatus.timeOfDeath = timeOfDeath;
            this.subjectChange.emit(this.subject);
        }
    }
    updateCauseOfDeath(event) {
        if (this.subject) {
            if (event) {
                this.selectedCauseOfDeath = event.value;
                this.subject.vitalStatus.causeOfDeath = new OntologyClass(event.value.id.value, event.value.lbl);
            } else {
                this.subject.vitalStatus.causeOfDeath = undefined;
            }
            this.subjectChange.emit(this.subject);
        }
    }

    getKaryotypicSexes() {
        return KaryotypicSex.VALUES;
    }
    getStatuses() {
        // tslint:disable-next-line:radix
        return Object.values(Status).filter(x => !(parseInt(x) >= 0));
    }
    getDeceasedStatus() {
        return Status.DECEASED;
    }

}
