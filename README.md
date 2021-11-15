# An example data generator for Java and TypeScript

To build the package, run
```
npm install
node createFrontend.js
rm src/main/resources/META-INF/frontend/example-data-bundle.*
cat src/main/resources/META-INF/resources/frontend/data-generator.ts|sed "s/example-data-bundle/example-data-bundle.esm/" > src/main/resources/META-INF/resources/frontend/data-generator.esm.ts
rollup -c rollup.config.cjs.js
rollup -c rollup.config.esm.js
mvn clean install
```
