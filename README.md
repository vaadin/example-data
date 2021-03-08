# An example data generator for Java and TypeScript

To build the package, run
```
npm install
node createFrontend.js
rm src/main/resources/META-INF/resources/frontend/example-data-bundle.js
rollup -c 
mvn clean install -Pdirectory
```
