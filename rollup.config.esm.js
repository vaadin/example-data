import { nodeResolve } from '@rollup/plugin-node-resolve';
import commonjs from 'rollup-plugin-commonjs';

export default {
  input: './rollup-index',
  output: {
    file: 'src/main/resources/META-INF/frontend/example-data-bundle.esm.js',
    format: 'esm',
    exports: 'named',
  },
  plugins: [nodeResolve(), commonjs()],
};
