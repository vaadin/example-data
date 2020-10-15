const chanceImport = require('chance');
const options: any = {};
/* Generator: randomOptions */

export enum DataType {
  ID = 'ID',
  FirstName = 'FIRST_NAME',
  LastName = 'LAST_NAME',
  FullName = 'FULL_NAME',
  Email = 'EMAIL',
  Domain = 'DOMAIN',
  Occupation = 'OCCUPATION',
  CompanyName = 'COMPANY_NAME',
  IBAN = 'IBAN',
  TransactionStatus = 'TRANSACTION_STATUS',
  ProfilePictureURL = 'PROFILE_PICTURE_URL',
  AmountOfMoney = 'AMOUNT_OF_MONEY',
  DateOfBirth = 'DATE_OF_BIRTH',
  PhoneNumber = 'PHONE_NUMBER',
  City = 'CITY',
  State = 'STATE',
  Country = 'COUNTRY',
  ZipCode = 'ZIP_CODE',
  Address = 'ADDRESS',
  BookTitle = 'BOOK_TITLE',
  BookTitlePrefix = 'BOOK_TITLE_PREFIX',
  BookTitleSuffix = 'BOOK_TITLE_SUFFIX',
  BookGenre = 'BOOK_GENRE',
}

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

const combine = (seed: number, types: DataType[]) => {
  return types.map((dataType) => DataGenerators[dataType].createValue(seed)).join(' ');
};

export const DataGenerators: { [key in string]: ValueCreator } = {
  [DataType.ID]: { createValue: (_seed) => idSequence++ },
  [DataType.FullName]: {
    createValue: (seed) => combine(seed, [DataType.FirstName, DataType.LastName]),
  },
  [DataType.BookTitle]: {
    createValue: (seed) => combine(seed, [DataType.BookTitlePrefix, DataType.BookTitleSuffix]),
  },
  [DataType.Email]: {
    createValue: (seed) =>
      DataGenerators[DataType.FirstName].createValue(seed).toLowerCase() +
      '.' +
      DataGenerators[DataType.LastName].createValue(seed).toLowerCase() +
      '@' +
      DataGenerators[DataType.Domain].createValue(seed),
  },
  [DataType.AmountOfMoney]: {
    createValue: (seed) => {
      setSeed(seed);
      return chance.integer({ min: 1000, max: 100000 });
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
    createValue: (seed) => {
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
  /* Generator: randomOptionGenerators */
};
