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
'use strict';

angular.module('activitiModeler')
.controller('EditorUnsavedChangesPopupCrtl', ['$rootScope', '$scope', '$http', '$location', '$window', function ($rootScope, $scope, $http, $location, $window) {

	$scope.ok = function () {
		if ($scope.handleResponseFunction) {
			$scope.handleResponseFunction(true);

            // Also clear any 'onbeforeunload', added by oryx
            $window.onbeforeunload = undefined;
		}
		$scope.$hide();
	};

	$scope.cancel = function () {
		if ($scope.handleResponseFunction) {
			$scope.handleResponseFunction(false);
		}
        $scope.$hide();
	};
}]);

activitiModule
.directive('autoFocus', ['$timeout', '$parse', function($timeout, $parse) {
    return {
        restrict: 'AC',
        compile: function($element, attr) {

            return function(_scope, _element, _attrs) {
                var firstChild = (_attrs.focusFirstChild !== undefined);
                $timeout(function () {
                    if (firstChild) {
                        // look for first input-element in child-tree and focus that
                        var inputs = _element.find('input');
                        if (inputs && inputs.length > 0) {
                            inputs[0].focus();
                        }
                    } else {
                        // Focus element where the directive is put on
                        _element[0].focus();
                    }
                }, 100);
            }
        }
    };
}]);
