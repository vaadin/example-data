'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var commonjsGlobal = typeof globalThis !== 'undefined' ? globalThis : typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};

function unwrapExports (x) {
	return x && x.__esModule && Object.prototype.hasOwnProperty.call(x, 'default') ? x['default'] : x;
}

function createCommonjsModule(fn, module) {
	return module = { exports: {} }, fn(module, module.exports), module.exports;
}

var common = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.helper = void 0;
exports.helper = {
    parseTail: (num, n) => {
        const ds = String(num);
        return [ds.slice(0, -n), ds.slice(-n)];
    },
    _invCharListMemo: {},
    invertCharList: (alphabet) => {
        if (exports.helper._invCharListMemo[alphabet] == null) {
            exports.helper._invCharListMemo[alphabet] = {};
            const len = alphabet.length;
            for (let i = 0; i < len; i += 1) {
                exports.helper._invCharListMemo[alphabet][alphabet[i]] = i;
            }
            if (len !== Object.keys(exports.helper._invCharListMemo[alphabet]).length) {
                throw new Error("assertion error: chars must be unique");
            }
        }
        return exports.helper._invCharListMemo[alphabet];
    },
    iso7064: {
        numeric: "0123456789X",
        alphabetic: "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        alphanumeric: "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ*",
        /** Implement ISO 7064 pure system recursive method. */
        computePure: (num, mod, radix, hasTwoCCs, alphabet) => {
            const ds = `${num}${alphabet[0]}${hasTwoCCs ? alphabet[0] : ""}`;
            const overflowProtection = Math.floor(0xffffffffffff / radix);
            const charmap = exports.helper.invertCharList(alphabet);
            let c = 0;
            for (let i = 0, len = ds.length; i < len; i += 1) {
                c = c * radix + charmap[ds[i]];
                if (c > overflowProtection) {
                    c %= mod;
                }
            }
            c = (mod + 1 - (c % mod)) % mod;
            if (hasTwoCCs) {
                return `${alphabet[Math.floor(c / radix)]}${alphabet[c % radix]}`;
            }
            return alphabet[c];
        },
        /** Implement ISO 7064 hybrid system recursive method. */
        computeHybrid: (ds, alphabet) => {
            const mod = alphabet.length;
            const charmap = exports.helper.invertCharList(alphabet);
            let c = mod;
            for (let i = 0, len = ds.length; i < len; i += 1) {
                c = (c % (mod + 1)) + charmap[ds[i]];
                c = (c % mod || mod) * 2;
            }
            c %= mod + 1;
            return alphabet[(mod + 1 - c) % mod];
        },
    },
};
});

unwrapExports(common);
common.helper;

var luhn = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.luhn = void 0;

/** Luhn algorithm implementation */
class Luhn {
    constructor() {
        this.name = "luhn";
        this.longName = "Luhn Algorithm";
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9]/g, "");
        const lookup = {
            0: 0,
            1: 2,
            2: 4,
            3: 6,
            4: 8,
            5: 1,
            6: 3,
            7: 5,
            8: 7,
            9: 9,
        };
        let sum = 0;
        let odd = 1;
        for (let i = ds.length - 1; i > -1; i -= 1) {
            sum += odd ? lookup[ds[i]] : Number(ds[i]);
            odd ^= 1;
            if (sum > 0xffffffffffff) {
                // ~2^48 at max
                sum %= 10;
            }
        }
        return String(10 - (sum % 10)).slice(-1);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.luhn = new Luhn();
});

unwrapExports(luhn);
luhn.luhn;

var verhoeff = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.verhoeff = void 0;

/**
 * Verhoeff algorithm implementation
 *
 * Note: There is not a firm consensus on the direction (left to right or right
 * to left) in which a Verhoeff calculator scans numeric text to construct an
 * input digit sequence. This implementation is hard coded to read a string from
 * right to left and append the check digit at the rightmost position, which is
 * a consistent behavior with other popular implementations. Reverse the input
 * string before calling this class' methods if you need to interpret a string
 * from left to right.
 */
class Verhoeff {
    constructor() {
        this.name = "verhoeff";
        this.longName = "Verhoeff Algorithm";
        /** Verhoeff multiplication table */
        this.d = [
            [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
            [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
            [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
            [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
            [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
            [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
            [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
            [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
            [9, 8, 7, 6, 5, 4, 3, 2, 1, 0],
        ];
        /** Verhoeff permutation table */
        this.p = [
            [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
            [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
            [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
            [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
            [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
            [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
            [7, 0, 4, 6, 9, 1, 3, 2, 5, 8],
        ];
        /** Verhoeff inverse table */
        this.inv = ["0", "4", "3", "2", "1", "5", "6", "7", "8", "9"];
    }
    compute(num) {
        const ds = `${String(num).replace(/[^0-9]/g, "")}0`;
        let c = 0;
        for (let i = 0, len = ds.length; i < len; i += 1) {
            c = this.d[c][this.p[i & 7][Number(ds[len - i - 1])]];
        }
        return this.inv[c];
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.verhoeff = new Verhoeff();
});

unwrapExports(verhoeff);
verhoeff.verhoeff;

var damm = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.damm = void 0;

/** Damm algorithm implementation */
class Damm {
    constructor() {
        this.name = "damm";
        this.longName = "Damm Algorithm";
        /** Damm operation table */
        this.opTable = [
            [0, 3, 1, 7, 5, 9, 8, 6, 4, 2],
            [7, 0, 9, 2, 1, 5, 4, 8, 6, 3],
            [4, 2, 0, 6, 8, 7, 1, 3, 5, 9],
            [1, 7, 5, 0, 9, 8, 3, 4, 2, 6],
            [6, 1, 2, 3, 0, 4, 5, 9, 7, 8],
            [3, 6, 7, 4, 2, 0, 9, 5, 8, 1],
            [5, 8, 6, 9, 7, 2, 0, 1, 3, 4],
            [8, 9, 4, 5, 3, 6, 2, 0, 1, 7],
            [9, 4, 3, 8, 6, 1, 7, 2, 0, 5],
            [2, 5, 8, 1, 4, 3, 6, 7, 9, 0],
        ];
    }
    compute(num) {
        const ds = `${String(num).replace(/[^0-9]/g, "")}`;
        let c = 0;
        for (let i = 0, len = ds.length; i < len; i += 1) {
            c = this.opTable[c][Number(ds[i])];
        }
        return String(c);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.damm = new Damm();
});

unwrapExports(damm);
damm.damm;

var mod11_2 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod11_2 = void 0;

/** ISO/IEC 7064, MOD 11-2 implementation */
class Mod11_2 {
    constructor() {
        this.name = "mod11_2";
        this.longName = "ISO/IEC 7064, MOD 11-2";
        this.alphabet = common.helper.iso7064.numeric;
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9]/g, "");
        return common.helper.iso7064.computePure(ds, 11, 2, false, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.mod11_2 = new Mod11_2();
});

unwrapExports(mod11_2);
mod11_2.mod11_2;

var mod37_2 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod37_2 = void 0;

/** ISO/IEC 7064, MOD 37-2 implementation */
class Mod37_2 {
    constructor() {
        this.name = "mod37_2";
        this.longName = "ISO/IEC 7064, MOD 37-2";
        this.alphabet = common.helper.iso7064.alphanumeric;
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9A-Z]/g, "");
        return common.helper.iso7064.computePure(ds, 37, 2, false, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.mod37_2 = new Mod37_2();
});

unwrapExports(mod37_2);
mod37_2.mod37_2;

var mod97_10 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod97_10 = void 0;

/** ISO/IEC 7064, MOD 97-10 implementation */
class Mod97_10 {
    constructor() {
        this.name = "mod97_10";
        this.longName = "ISO/IEC 7064, MOD 97-10";
    }
    compute(num) {
        const ds = `${String(num).replace(/[^0-9]/g, "")}00`;
        // Simplified procedure as described in ISO/IEC 7064
        let c = Number(ds.slice(0, 14)) % 97; // 10^14 < 2^48
        for (let i = 14, len = ds.length; i < len; i += 12) {
            c = Number(String(c) + ds.slice(i, i + 12)) % 97;
        }
        return `0${(98 - c) % 97}`.slice(-2);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 2);
    }
}
exports.mod97_10 = new Mod97_10();
});

unwrapExports(mod97_10);
mod97_10.mod97_10;

var mod661_26 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod661_26 = void 0;

/** ISO/IEC 7064, MOD 661-26 implementation */
class Mod661_26 {
    constructor() {
        this.name = "mod661_26";
        this.longName = "ISO/IEC 7064, MOD 661-26";
        this.alphabet = common.helper.iso7064.alphabetic;
    }
    compute(num) {
        const ds = String(num).replace(/[^A-Z]/g, "");
        return common.helper.iso7064.computePure(ds, 661, 26, true, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 2);
    }
}
exports.mod661_26 = new Mod661_26();
});

unwrapExports(mod661_26);
mod661_26.mod661_26;

var mod1271_36 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod1271_36 = void 0;

/** ISO/IEC 7064, MOD 1271-36 implementation */
class Mod1271_36 {
    constructor() {
        this.name = "mod1271_36";
        this.longName = "ISO/IEC 7064, MOD 1271-36";
        this.alphabet = common.helper.iso7064.alphanumeric;
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9A-Z]/g, "");
        return common.helper.iso7064.computePure(ds, 1271, 36, true, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 2);
    }
}
exports.mod1271_36 = new Mod1271_36();
});

unwrapExports(mod1271_36);
mod1271_36.mod1271_36;

var mod11_10 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod11_10 = void 0;

/** ISO/IEC 7064, MOD 11-10 implementation */
class Mod11_10 {
    constructor() {
        this.name = "mod11_10";
        this.longName = "ISO/IEC 7064, MOD 11-10";
        this.alphabet = common.helper.iso7064.numeric.slice(0, -1);
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9]/g, "");
        return common.helper.iso7064.computeHybrid(ds, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.mod11_10 = new Mod11_10();
});

unwrapExports(mod11_10);
mod11_10.mod11_10;

var mod27_26 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod27_26 = void 0;

/** ISO/IEC 7064, MOD 27-26 implementation */
class Mod27_26 {
    constructor() {
        this.name = "mod27_26";
        this.longName = "ISO/IEC 7064, MOD 27-26";
        this.alphabet = common.helper.iso7064.alphabetic;
    }
    compute(num) {
        const ds = String(num).replace(/[^A-Z]/g, "");
        return common.helper.iso7064.computeHybrid(ds, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.mod27_26 = new Mod27_26();
});

unwrapExports(mod27_26);
mod27_26.mod27_26;

var mod37_36 = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.mod37_36 = void 0;

/** ISO/IEC 7064, MOD 37-36 implementation */
class Mod37_36 {
    constructor() {
        this.name = "mod37_36";
        this.longName = "ISO/IEC 7064, MOD 37-36";
        this.alphabet = common.helper.iso7064.alphanumeric.slice(0, -1);
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9A-Z]/g, "");
        return common.helper.iso7064.computeHybrid(ds, this.alphabet);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.mod37_36 = new Mod37_36();
});

unwrapExports(mod37_36);
mod37_36.mod37_36;

var gtin = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.gtin = void 0;

/**
 * Standard check digit algorithm for GS1 data structures (including GTIN)
 *
 * Note: This implementation does not check the length of a number; however, it
 * is not recommended to use numbers longer than 18 digits because GS1 General
 * Specifications do not explicitly specify an algorithm for them.
 */
class GTIN {
    constructor() {
        this.name = "gtin";
        this.longName = "GTINs (including UPC, EAN, ISBN-13, etc.)";
    }
    compute(num) {
        const ds = String(num).replace(/[^0-9]/g, "");
        let sum = 0;
        let odd = 1;
        for (let i = ds.length - 1; i > -1; i -= 1) {
            sum += Number(ds[i]) * (odd ? 3 : 1);
            odd ^= 1;
            if (sum > 0xffffffffffff) {
                // ~2^48 at max
                sum %= 10;
            }
        }
        return String(10 - (sum % 10)).slice(-1);
    }
    generate(num) {
        return `${num}${this.compute(num)}`;
    }
    validate(num) {
        const [src, cc] = this.parse(num);
        return this.compute(src) === cc;
    }
    parse(num) {
        return common.helper.parseTail(num, 1);
    }
}
exports.gtin = new GTIN();
});

unwrapExports(gtin);
gtin.gtin;

var lib = createCommonjsModule(function (module, exports) {
/**
 * cdigit
 *
 * @copyright 2018-2020 LiosK
 * @license Apache-2.0
 */
var __createBinding = (commonjsGlobal && commonjsGlobal.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    Object.defineProperty(o, k2, { enumerable: true, get: function() { return m[k]; } });
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __exportStar = (commonjsGlobal && commonjsGlobal.__exportStar) || function(m, exports) {
    for (var p in m) if (p !== "default" && !Object.prototype.hasOwnProperty.call(exports, p)) __createBinding(exports, m, p);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.names = void 0;
// Popular generic algorithms
__exportStar(luhn, exports);
__exportStar(verhoeff, exports);
__exportStar(damm, exports);
// ISO/IEC 7064:2003, Pure systems
__exportStar(mod11_2, exports);
__exportStar(mod37_2, exports);
__exportStar(mod97_10, exports);
__exportStar(mod661_26, exports);
__exportStar(mod1271_36, exports);
// ISO/IEC 7064:2003, Hybrid systems
__exportStar(mod11_10, exports);
__exportStar(mod27_26, exports);
__exportStar(mod37_36, exports);
// GTINs (including UPC, EAN, ISBN-13, etc.)
__exportStar(gtin, exports);
/** Supported cdigit names */
exports.names = [
    "luhn",
    "verhoeff",
    "damm",
    "mod11_2",
    "mod37_2",
    "mod97_10",
    "mod661_26",
    "mod1271_36",
    "mod11_10",
    "mod27_26",
    "mod37_36",
    "gtin",
];
});

var index = unwrapExports(lib);
var lib_1 = lib.names;

exports.default = index;
exports.names = lib_1;
