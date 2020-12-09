import { DataType } from './data-generator-types';
const chanceImport = require('chance');
const cdigit = require('cdigit');
const options: any = {};

/* Generator: randomOptions */

export interface ValueCreator {
  createValue(seed: number): any;
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

const combine = (seed: number, sameSeed: boolean, seedOffset: number, types: DataType[]) => {
  seed += seedOffset;
  const values = [];
  for (let i = 0; i < types.length; i++) {
    const value = DataGenerators[types[i]].createValue(seed);
    values.push(value);
    if (!sameSeed) {
      seed++;
    }
  }
  return values.join(' ');
};

const dateMaxDaysBack = (seed: number, maxDaysBack: number): string => {
  setSeed(seed);
  const daysBack = chance.integer({ min: 0, max: maxDaysBack });
  const date: Date = new Date(new Date().getTime() - daysBack * 24 * 3600 * 1000);
  return date.toISOString().split('T')[0];
};

export const DataGenerators: { [key in string]: ValueCreator } = {
  [DataType.ID]: { createValue: (_seed) => idSequence++ },
  [DataType.FullName]: {
    createValue: (seed) => combine(seed, true, 0, [DataType.FirstName, DataType.LastName]),
  },
  [DataType.BookTitle]: {
    createValue: (seed) => combine(seed, true, 0, [DataType.BookTitlePrefix, DataType.BookTitleSuffix]),
  },
  [DataType.Email]: {
    createValue: (seed) => {
      const email =
        DataGenerators[DataType.FirstName].createValue(seed).toLowerCase() +
        '.' +
        DataGenerators[DataType.LastName].createValue(seed).toLowerCase() +
        '@' +
        DataGenerators[DataType.Domain].createValue(seed);
      return email.replace(/ /g, '');
    },
  },
  [DataType.AmountOfMoney]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.integer({ min: 1000, max: 100000 });
    },
  },
  [DataType.Price]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.floating({ min: 1, max: 500, fixed: 2 });
    },
  },
  [DataType.FirstName]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.first();
    },
  },
  [DataType.LastName]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.last();
    },
  },
  [DataType.CompanyName]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.company();
    },
  },
  [DataType.Domain]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.domain();
    },
  },
  [DataType.DateOfBirth]: {
    createValue: (seed): string => {
      setSeed(seed);
      return chance.birthday().toISOString().split('T')[0];
    },
  },
  [DataType.PhoneNumber]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.phone();
    },
  },
  [DataType.City]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.city();
    },
  },
  [DataType.State]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.state({ full: true });
    },
  },
  [DataType.Country]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.country({ full: true });
    },
  },
  [DataType.ZipCode]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.zip();
    },
  },
  [DataType.Address]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.address();
    },
  },
  [DataType.Word]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.word();
    },
  },
  [DataType.TwoWords]: {
    createValue: (seed) => {
      return combine(seed, false, 1, [DataType.Word, DataType.Word]);
    },
  },
  [DataType.Sentence]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.sentence();
    },
  },
  [DataType.Ean13]: {
    createValue: (seed) => {
      setSeed(seed);
      return cdigit.gtin.generate(chance.integer({ min: 1, max: 999999999999 }));
    },
  },
  [DataType.NumberUpTo10]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 10 });
    },
  },
  [DataType.NumberUpTo100]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 100 });
    },
  },
  [DataType.NumberUpTo1000]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 1000 });
    },
  },
  [DataType.NumberUpTo10000]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.integer({ min: 1, max: 10000 });
    },
  },
  [DataType.FoodProductEan]: {
    createValue: (seed) => {
      return random(options.FoodProducts, seed).split('\t')[0];
    },
  },
  [DataType.FoodProductName]: {
    createValue: (seed) => {
      return random(options.FoodProducts, seed).split('\t')[1];
    },
  },
  [DataType.FoodProductImage]: {
    createValue: (seed) => {
      return random(options.FoodProducts, seed).split('\t')[2];
    },
  },
  [DataType.BookImageUrl]: {
    createValue: (seed) => {
      const title = DataGenerators[DataType.BookTitle].createValue(seed);
      const author = DataGenerators[DataType.FullName].createValue(seed);
      const imageBackgroundUrl = DataGenerators[DataType.BookImageBackground].createValue(seed);

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
    createValue: (seed) => {
      setSeed(seed);
      return chance.bool({ likelihood: 50 });
    },
  },
  [DataType.Boolean_90_10]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.bool({ likelihood: 90 });
    },
  },
  [DataType.Boolean_10_90]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.bool({ likelihood: 10 });
    },
  },
  [DataType.DateLast10Years]: {
    createValue: (seed) => {
      return dateMaxDaysBack(seed, 3650);
    },
  },
  [DataType.DateLast1Year]: {
    createValue: (seed) => {
      return dateMaxDaysBack(seed, 365);
    },
  },
  [DataType.DateLast30Days]: {
    createValue: (seed) => {
      return dateMaxDaysBack(seed, 30);
    },
  },
  [DataType.DateLast7days]: {
    createValue: (seed) => {
      return dateMaxDaysBack(seed, 7);
    },
  },

  /* Generator: randomOptionGenerators */
};
