const chance = require('chance').Chance();
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
  PhoneNumber = 'PHONE_NUMBER'
}

export interface ValueCreator {
  createValue(): any;
}

const random = (array: string[]) => {
  const idx = Math.random() * array.length;
  return array[Math.floor(idx)];
};

let idSequence = 1;
export const DataGenerators: { [key in string]: ValueCreator } = {
  [DataType.ID]: { createValue: () => idSequence++ },
  [DataType.FullName]: {
    createValue: () =>
      DataGenerators[DataType.FirstName].createValue() + ' ' + DataGenerators[DataType.LastName].createValue(),
  },
  [DataType.Email]: {
    createValue: () =>
      DataGenerators[DataType.FirstName].createValue().toLowerCase() +
      '.' +
      DataGenerators[DataType.LastName].createValue().toLowerCase() +
      '@' +
      DataGenerators[DataType.Domain].createValue(),
  },
  [DataType.AmountOfMoney]: {
    createValue: () => {
      const min = 1000;
      const max = 100000;
      return Math.random() * (max - min) + min;
    },
  },
  [DataType.FirstName]: {
    createValue: () => {
      return chance.first();
    }
  },
  [DataType.LastName]: {
    createValue: () => {
      return chance.last();
    }
  },
  [DataType.CompanyName]: {
    createValue: () => {
      return chance.company();
    }
  },
  [DataType.Domain]: {
    createValue: () => {
      return chance.domain();
    }
  },
  [DataType.DateOfBirth]: {
    createValue: () => {
      return chance.birthday().toISOString().split("T")[0];
    }
  },
  [DataType.PhoneNumber]: {
    createValue: () => {
      return chance.phone();
    }
  },
  /* Generator: randomOptionGenerators */
};
