import { Pipe, PipeTransform, Injectable } from '@angular/core';
import {Article} from "../model/article";

@Pipe({
  name: 'filter',
})
@Injectable()
export class FilterPipe implements PipeTransform {
  transform(items: Article[], field: string, value: string): any[] {
    if (!items) {
      return [];
    }
    if (!field || !value) {
      return items;
    }

    // @ts-ignore
    return items.filter((singleItem) =>
      // @ts-ignore
      singleItem[field].toLowerCase().includes(value.toLowerCase())
    );
  }
}
