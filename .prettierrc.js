// prettier.config.js or .prettierrc.js
module.exports = {
  tabWidth: 2,
  semi: true,
  singleQuote: true,
  printWidth: 120,
  arrowParens: 'always',
  trailingComma: 'es5',
  overrides: [
    {
      files: '*.java',
      options: {
        tabWidth: 4,
      },
    },
  ],
};
