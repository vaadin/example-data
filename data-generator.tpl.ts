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
  /* Generator: randomOptionGenerators */
};
