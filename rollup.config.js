import { nodeResolve } from '@rollup/plugin-node-resolve';
import commonjs from 'rollup-plugin-commonjs';

export default {
  input: 'cdigit',
  output: {
    file: 'src/main/resources/cdigit.js',
    format: 'cjs'
  },
  plugins: [nodeResolve(),    commonjs()]
};
