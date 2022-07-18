import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { ComponentsModule } from 'src/app/component/components.module';

@Component({
  selector: 'app-search-filter',
  templateUrl: './search-filter.component.html',
  styleUrls: ['./search-filter.component.scss']
})

export class SearchFilterComponent {
  myFormControl = new FormControl();
  @Input()
  title: string;
  @Input()
  options: string[];
  @Output()
  onSelected = new EventEmitter<string>();
  filteredOptions: Observable<string[]>;

  ngOnInit() {
    this.filteredOptions = this.myFormControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filterOptions(value))
      );
  }

  private _filterOptions(value: string): string[] {
    const filterValue = value.toLowerCase();
    let result = this.options.filter(val => val.toLowerCase().includes(filterValue));
    if (result.length === 0) {
      return [ value ];
    }
    return result;
  }

  selectItem(selected?: any) {
    if (selected === undefined) {
      this.onSelected.emit('');
    } else {
      this.onSelected.emit(selected);
    }
  }
}