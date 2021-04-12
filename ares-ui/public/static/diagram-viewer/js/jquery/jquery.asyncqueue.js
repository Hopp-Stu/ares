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
(function($){
    $.AsyncQueue = function() {
        var that = this,
            queue = [],
			completeFunc,
            failureFunc,
            paused = false,
            lastCallbackData,
            _run,
			_complete,
			inQueue = 0,
			defaultTimeOut = 10;

        _run = function() {
            var f = queue.shift();

            if (f) {
				inQueue++;
				setTimeout(function(){
					f.fn.apply(that, [that]);

					if (!f.isParallel)
						if (paused === false) {
							_run();
						}
					inQueue --;
					if (inQueue == 0 && queue.length == 0)
						_complete();
				}, f.timeOut);

				if (f.isParallel)
					if (paused === false) {
						_run();
					}
            }
        };

		_complete = function(){
			if (completeFunc)
					completeFunc.apply(that, [that]);
		};

		this.onComplete = function(func) {
            completeFunc = func;
        };

		this.onFailure = function(func) {
            failureFunc = func;
        };

        this.add = function(func) {
			// TODO: add callback for queue[i] complete

			var obj = arguments[0];
			if (obj && Object.prototype.toString.call(obj) === "[object Array]") {
				var fn = arguments[1];
				var timeOut = (typeof(arguments[2]) != "undefined")? arguments[2] : defaultTimeOut;
				if (typeof(fn) == "function") {
					for(var i = 0; i < obj.length; i++) {
						var f = function(objx){
							queue.push({isParallel: true, fn: function(){fn.apply(that, [that, objx]);}, timeOut: timeOut});
						}(obj[i])
					}
				}
			} else {
				var fn = arguments[0];
				var timeOut = (typeof(arguments[1]) != "undefined")? arguments[2] : defaultTimeOut;
				queue.push({isParallel: false, fn: func, timeOut: timeOut});
			}
            return this;
        };

		this.addParallel = function(func, timeOut) {
			// TODO: add callback for queue[i] complete

            queue.push({isParallel: true, fn: func, timeOut: timeOut});
            return this;
        };

        this.storeData = function(dataObject) {
            lastCallbackData = dataObject;
            return this;
        };

        this.lastCallbackData = function () {
            return lastCallbackData;
        };

        this.run = function() {
            paused = false;
            _run();
        };

        this.pause = function () {
            paused = true;
            return this;
        };

        this.failure = function() {
            paused = true;
            if (failureFunc) {
                var args = [that];
                for(i = 0; i < arguments.length; i++) {
                    args.push(arguments[i]);
                }
                failureFunc.apply(that, args);
            }
        };

		this.size = function(){
			return queue.length;
		};

        return this;
    }
})(jQuery);
