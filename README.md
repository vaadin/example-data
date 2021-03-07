# An example data generator for Java and TypeScript

To build the package, run
```
npm install
node createFrontend.js
rm src/main/resources/cdigit.js
rollup -c 
cp node_modules/chance/chance.js src/main/resources/chance.js 
mvn clean install -Pdirectory
```
