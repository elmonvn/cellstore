import {Color} from './color.enum';

export class Phone {
  id: string;
  code: string;
  model: string;
  brand: string;
  price: number;
  photo: string;
  startDate: Date;
  endDate: Date;
  color: Color;
}
