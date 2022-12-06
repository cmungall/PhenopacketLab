import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { CommonModule } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio';
import { RouterModule } from '@angular/router';
import { MatRippleModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatTreeModule } from '@angular/material/tree';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressBarModule } from '@angular/material/progress-bar';

import { DropdownModule } from 'primeng/dropdown';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { InputNumberModule } from 'primeng/inputnumber';
import { MessageService } from 'primeng/api';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { TriStateCheckboxModule } from 'primeng/tristatecheckbox';
import { ChipsModule } from 'primeng/chips';
import { TreeModule } from 'primeng/tree';
import { AccordionModule } from 'primeng/accordion';
import { TreeSelectModule } from 'primeng/treeselect';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CheckboxModule } from 'primeng/checkbox';

import { MessageDialogComponent } from './message-dialog/message-dialog.component';
import { SpinnerDialogComponent } from './spinner-dialog/spinner-dialog.component';
import { SearchFilterComponent } from './time-element/search-filter/search-filter.component';
import { TimeElementComponent } from './time-element/time-element.component';
import { AgeComponent } from './time-element/age/age.component';
import { SearchBoxComponent } from './search-box/search-box.component';
import { OntologyClassComponent } from './ontology-class/ontology-class.component';
import { TreeSelectComponent } from './ontology-class/tree-select/tree-select.component';
import { TreeSearchComponent } from './ontology-class/tree-search/tree-search.component';
import { DiseaseEditComponent } from './edit/disease-edit/disease-edit.component';
import { PhenotypicFeatureEditComponent } from './edit/phenotypic-feature-edit/phenotypic-feature-edit.component';
import { IndividualEditComponent } from './edit/individual-edit/individual-edit.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MatRadioModule,
    MatRippleModule,
    MatAutocompleteModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatTooltipModule,
    MatIconModule,
    MatSelectModule,
    MatListModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    DropdownModule,
    InputNumberModule,
    MessagesModule,
    MessageModule,
    CalendarModule,
    InputTextModule,
    TriStateCheckboxModule,
    ChipsModule,
    AccordionModule,
    TreeSelectModule,
    TreeModule,
    MatCardModule,
    MatTreeModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonToggleModule,
    MatProgressBarModule,
    RadioButtonModule,
    CheckboxModule,

  ],
  declarations: [
    MessageDialogComponent,
    SpinnerDialogComponent,
    TimeElementComponent,
    SearchFilterComponent,
    AgeComponent,
    SearchBoxComponent,
    TreeSelectComponent,
    OntologyClassComponent,
    TreeSearchComponent,
    DiseaseEditComponent,
    PhenotypicFeatureEditComponent,
    IndividualEditComponent
  ],
  exports: [
    MessageDialogComponent,
    SpinnerDialogComponent,
    TimeElementComponent,
    SearchFilterComponent,
    AgeComponent,
    SearchBoxComponent,
    TreeSelectComponent,
    OntologyClassComponent,
    TreeSearchComponent,
    DiseaseEditComponent,
    PhenotypicFeatureEditComponent,
    IndividualEditComponent
  ],
  providers: [MessageService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class SharedModule { }
