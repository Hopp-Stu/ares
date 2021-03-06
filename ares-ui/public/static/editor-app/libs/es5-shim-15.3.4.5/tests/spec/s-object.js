/*******************************************************************************
 * Copyright (c) 2021 - 9999, ARES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

describe('Object', function () {
    "use strict";

    describe("Object.keys", function () {
        var obj = {
            "str": "boz",
            "obj": { },
            "arr": [],
            "bool": true,
            "num": 42,
            "null": null,
            "undefined": undefined
        };

        var loopedValues = [];
        for (var k in obj) {
            loopedValues.push(k);
        }

        var keys = Object.keys(obj);
        it('should have correct length', function () {
            expect(keys.length).toBe(7);
        });

        it('should return an Array', function () {
            expect(Array.isArray(keys)).toBe(true);
        });

        it('should return names which are own properties', function () {
            keys.forEach(function (name) {
                expect(obj.hasOwnProperty(name)).toBe(true);
            });
        });

        it('should return names which are enumerable', function () {
            keys.forEach(function (name) {
                expect(loopedValues.indexOf(name)).toNotBe(-1);
            })
        });

        it('should throw error for non object', function () {
            var e = {};
            expect(function () {
                try {
                    Object.keys(42)
                } catch (err) {
                    throw e;
                }
            }).toThrow(e);
        });
    });

	describe("Object.isExtensible", function () {
        var obj = { };

        it('should return true if object is extensible', function () {
            expect(Object.isExtensible(obj)).toBe(true);
        });

        it('should return false if object is not extensible', function () {
            expect(Object.isExtensible(Object.preventExtensions(obj))).toBe(false);
        });

        it('should return false if object is seal', function () {
            expect(Object.isExtensible(Object.seal(obj))).toBe(false);
        });

        it('should return false if object is freeze', function () {
            expect(Object.isExtensible(Object.freeze(obj))).toBe(false);
        });

        it('should throw error for non object', function () {
            var e1 = {};
            expect(function () {
                try {
                    Object.isExtensible(42)
                } catch (err) {
                    throw e1;
                }
            }).toThrow(e1);
        });
    });

	describe("Object.defineProperty", function () {
        var obj;

        beforeEach(function() {
           obj = {};

           Object.defineProperty(obj, 'name', {
               value : 'Testing',
               configurable: true,
               enumerable: true,
               writable: true
           });
        });

        it('should return the initial value', function () {
            expect(obj.hasOwnProperty('name')).toBeTruthy();
            expect(obj.name).toBe('Testing');
        });

        it('should be setable', function () {
            obj.name = 'Other';
            expect(obj.name).toBe('Other');
        });

        it('should return the parent initial value', function () {
            var child = Object.create(obj, {});

            expect(child.name).toBe('Testing');
            expect(child.hasOwnProperty('name')).toBeFalsy();
        });

        it('should not override the parent value', function () {
            var child = Object.create(obj, {});

            Object.defineProperty(child, 'name', {
                value : 'Other'
            });

            expect(obj.name).toBe('Testing');
            expect(child.name).toBe('Other');
        });

        it('should throw error for non object', function () {
            expect(function () {
                Object.defineProperty(42, 'name', {});
            }).toThrow();
        });
    });

	describe("Object.getOwnPropertyDescriptor", function () {
        it('should return undefined because the object does not own the property', function () {
            var descr = Object.getOwnPropertyDescriptor({}, 'name');

            expect(descr).toBeUndefined()
        });

        it('should return a data descriptor', function () {
            var descr = Object.getOwnPropertyDescriptor({name: 'Testing'}, 'name');

            expect(descr).not.toBeUndefined();
            expect(descr.value).toBe('Testing');
            expect(descr.writable).toBe(true);
            expect(descr.enumerable).toBe(true);
            expect(descr.configurable).toBe(true);
        });

        it('should return undefined because the object does not own the property', function () {
            var descr = Object.getOwnPropertyDescriptor(Object.create({name: 'Testing'}, {}), 'name');

            expect(descr).toBeUndefined()
        });

        it('should return a data descriptor', function () {
            var obj = Object.create({}, {
                name: {
                    value : 'Testing',
                    configurable: true,
                    enumerable: true,
                    writable: true
                }
            });

            var descr = Object.getOwnPropertyDescriptor(obj, 'name');

            expect(descr).not.toBeUndefined();
            expect(descr.value).toBe('Testing');
            expect(descr.writable).toBe(true);
            expect(descr.enumerable).toBe(true);
            expect(descr.configurable).toBe(true);
        });

    	it('should throw error for non object', function () {
            expect(function () {
                Object.getOwnPropertyDescriptor(42, 'name');
            }).toThrow();
        });
    });
});
