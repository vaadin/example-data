import { DataType } from './data-generator-types';
import { v4 as uuidv4 } from 'uuid';
const chanceImport = require('chance');
const cdigit = require('cdigit');
const options: any = {};

/* Generator: randomOptions */

export interface ValueCreator {
  createValue(seed: number, refTime: number): any;
}

const random = (array: string[], seed: number) => {
  setSeed(seed);
  return array[chance.integer({ min: 0, max: array.length - 1 })];
};

let idSequence = 1;
let chance = chanceImport.Chance(123);
const setSeed = (seed: number) => {
  chance = chanceImport.Chance(seed);
};

const combine = (seed: number, refTime: number, sameSeed: boolean, seedOffset: number, types: DataType[]) => {
  seed += seedOffset;
  const values = [];
  for (let i = 0; i < types.length; i++) {
    const value = DataGenerators[types[i]].createValue(seed, refTime);
    values.push(value);
    if (!sameSeed) {
      seed++;
    }
  }
  return values.join(' ');
};

const dateMaxDaysBack = (seed: number, refTime: number, maxDaysBack: number): string => {
  setSeed(seed);
  const daysBack = chance.integer({ min: 0, max: maxDaysBack });
  const date: Date = new Date(refTime - daysBack * 24 * 3600 * 1000);
  return date.toISOString().split('T')[0];
};
const dateMaxDaysForward = (seed: number, refTime: number, maxDaysForward: number): string => {
  setSeed(seed);
  const daysForward = chance.integer({ min: 0, max: maxDaysForward });
  const date: Date = new Date(refTime + daysForward * 24 * 3600 * 1000);
  return date.toISOString().split('T')[0];
};
const format2Digits = (nr: number): string => {
  return nr < 10 ? '0' + nr : '' + nr;
};
const formatTime = (hour: number, minute: number, second: number): string => {
  const hourString = format2Digits(hour);
  const minuteString = format2Digits(minute);
  const secondString = format2Digits(second);

  return `${hourString}:${minuteString}:${secondString}`;
};
export const DataGenerators: { [key in string]: ValueCreator } = {
  [DataType.ID]: { createValue: (_seed, _refTime) => idSequence++ },
  [DataType.UUID]: { createValue: (_seed, _refTime) => uuidv4() },
  [DataType.FullName]: {
    createValue: (seed, refTime) => combine(seed, refTime, true, 0, [DataType.FirstName, DataType.LastName]),
  },
  [DataType.BookTitle]: {
    createValue: (seed, refTime) =>
      combine(seed, refTime, true, 0, [DataType.BookTitlePrefix, DataType.BookTitleSuffix]),
  },
  [DataType.Email]: {
    createValue: (seed, refTime) => {
      const email =
        DataGenerators[DataType.FirstName].createValue(seed, refTime).toLowerCase() +
        '.' +
        DataGenerators[DataType.LastName].createValue(seed, refTime).toLowerCase() +
        '@' +
        DataGenerators[DataType.Domain].createValue(seed, refTime);
      return email.replace(/ /g, '');
    },
  },
  [DataType.AmountOfMoney]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.integer({ min: 1000, max: 100000 });
    },
  },
  [DataType.Price]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.floating({ min: 1, max: 500, fixed: 2 });
    },
  },
  [DataType.FirstName]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.first();
    },
  },
  [DataType.LastName]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.last();
    },
  },
  [DataType.CompanyName]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.company();
    },
  },
  [DataType.Domain]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.domain();
    },
  },
  [DataType.DateOfBirth]: {
    createValue: (seed, refTime): string => {
      return dateMaxDaysBack(seed, refTime, 365 * 100);
    },
  },
  [DataType.PhoneNumber]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.phone();
    },
  },
  [DataType.City]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.city();
    },
  },
  [DataType.State]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.state({ full: true });
    },
  },
  [DataType.Country]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.country({ full: true });
    },
  },
  [DataType.ZipCode]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.zip();
    },
  },
  [DataType.Address]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.address();
    },
  },
  [DataType.Word]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.word();
    },
  },
  [DataType.TwoWords]: {
    createValue: (seed, refTime) => {
      return combine(seed, refTime, false, 1, [DataType.Word, DataType.Word]);
    },
  },
  [DataType.Sentence]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.sentence();
    },
  },
  [DataType.Ean13]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return cdigit.gtin.generate(chance.integer({ min: 1, max: 999999999999 }));
    },
  },
  [DataType.NumberUpTo10]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 10 });
    },
  },
  [DataType.NumberUpTo100]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 100 });
    },
  },
  [DataType.NumberUpTo1000]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 1000 });
    },
  },
  [DataType.NumberUpTo10000]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 10000 });
    },
  },
  [DataType.FoodProductEan]: {
    createValue: (seed, _refTime) => {
      return random(options.FoodProducts, seed).split('\t')[0];
    },
  },
  [DataType.FoodProductName]: {
    createValue: (seed, _refTime) => {
      return random(options.FoodProducts, seed).split('\t')[1];
    },
  },
  [DataType.FoodProductImage]: {
    createValue: (seed, _refTime) => {
      return random(options.FoodProducts, seed).split('\t')[2];
    },
  },
  [DataType.BookImageUrl]: {
    createValue: (seed, refTime) => {
      const title = DataGenerators[DataType.BookTitle].createValue(seed, refTime);
      const author = DataGenerators[DataType.FullName].createValue(seed, refTime);
      const imageBackgroundUrl = DataGenerators[DataType.BookImageBackground].createValue(seed, refTime);

      const template = options['bookcover.svg.tpl'].join('\n');
      return (
        'data:image/svg+xml;utf8,' +
        encodeURIComponent(
          template.replace('#title#', title).replace('#image#', imageBackgroundUrl).replace('#author#', author)
        )
      );
    },
  },
  [DataType.Boolean_50_50]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.bool({ likelihood: 50 });
    },
  },
  [DataType.Boolean_90_10]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.bool({ likelihood: 90 });
    },
  },
  [DataType.Boolean_10_90]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      return chance.bool({ likelihood: 10 });
    },
  },
  [DataType.DateLast10Years]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysBack(seed, refTime, 365 * 10);
    },
  },
  [DataType.DateLast1Year]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysBack(seed, refTime, 365);
    },
  },
  [DataType.DateLast30Days]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysBack(seed, refTime, 30);
    },
  },
  [DataType.DateLast7days]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysBack(seed, refTime, 7);
    },
  },
  [DataType.DateNext10Years]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysForward(seed, refTime, 365 * 10);
    },
  },
  [DataType.DateNext1Year]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysForward(seed, refTime, 365);
    },
  },
  [DataType.DateNext30Days]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysForward(seed, refTime, 30);
    },
  },
  [DataType.DateNext7days]: {
    createValue: (seed, refTime) => {
      return dateMaxDaysForward(seed, refTime, 7);
    },
  },
  [DataType.TimeRandom]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      const hour = chance.integer({ min: 0, max: 24 });
      const minute = chance.integer({ min: 0, max: 59 });
      const second = chance.integer({ min: 0, max: 59 });
      return formatTime(hour, minute, second);
    },
  },
  [DataType.TimeHours]: {
    createValue: (seed, _refTime) => {
      setSeed(seed);
      const hour = chance.integer({ min: 0, max: 24 });
      const minute = 0;
      const second = 0;
      return formatTime(hour, minute, second);
    },
  },

  /* Generator: randomOptionGenerators */
};
