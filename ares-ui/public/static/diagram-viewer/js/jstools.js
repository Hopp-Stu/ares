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

if (typeof(console) == "undefined") {
  var console = {
    info: function(){},
    warn: function(){},
    error: function(){},
    log: function(){},
    time: function(){},
    timeEnd: function(){}
  };
}

if(!Array.isArray) {
  Array.isArray = function (vArg) {
    return Object.prototype.toString.call(vArg) === "[object Array]";
  };
}

if (!Object.isSVGElement) {
  Object.isSVGElement = function(vArg) {
  var str = Object.prototype.toString.call(vArg);
  return (str.indexOf("[object SVG") == 0);
  };
}
