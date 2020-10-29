const fs = require('fs');
const path = require('path');
const prettier = require('prettier');

const targetFile = 'src/main/resources/META-INF/resources/frontend/data-generator.ts';
const template = 'data-generator.tpl.ts';
const inputFilePath = 'src/main/resources/org/vaadin/artur/exampledata';

let generatorRandomOptions = '';
let generatorRandomOptionGenerators = '';
fs.readdirSync(inputFilePath).forEach((file) => {
  const optionName = file.replace('.txt', '');
  const options = fs.readFileSync(inputFilePath + '/' + file, { encoding: 'UTF-8' });
  const optionsArray = JSON.stringify(options.split('\n').filter((value) => value.trim().length > 0));

  // options.FirstName = [...];
  generatorRandomOptions += `options.${optionName} = ${optionsArray};\n\n`;

  // [DataType.CompanyName]: { createValue: () => random(options.companyName) },
  if (optionName != 'FoodProducts') {
    generatorRandomOptionGenerators += `  [DataType.${optionName}]: { createValue: (seed) => random(options.${optionName}, seed)},\n`;
  }
});

let contents = fs.readFileSync(template, { encoding: 'UTF-8' });
contents = contents.replace('  /* Generator: randomOptionGenerators */', generatorRandomOptionGenerators);
contents = contents.replace('/* Generator: randomOptions */', generatorRandomOptions);
fs.mkdirSync(path.dirname(targetFile), { recursive: true });

prettier.resolveConfigFile().then((filePath) => {
  prettier.resolveConfig(filePath).then((options) => {
    options.parser = 'typescript';
    contents = prettier.format(contents, options);
    fs.writeFileSync(targetFile, contents);
  });
});
