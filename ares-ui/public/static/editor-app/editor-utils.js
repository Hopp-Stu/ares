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

/**
 * Utility methods are grouped together here.
 */
var EDITOR = EDITOR || {};

EDITOR.UTIL = {

    getParameterByName: function (name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    },

    /**
     * Starts at the provided start element, and walks all preceding elements in the graph.
     * Each element is tested to have a certain property and, if it has, adds this property value
     * to the return result list.
     */
    collectPropertiesFromPrecedingElements: function (startElement, propertyType) {
        var visitedElements = [];
        var collectedProperties = [];
        EDITOR.UTIL._visitElementAndCollectProperty(startElement, propertyType, visitedElements, collectedProperties);
        return collectedProperties;
    },

    /**
     * Starts at the provided start element, and walks all preceding elements in the graph.
     * Each element is tested to be a specific stencil id and, if it has, adds the element
     * to the return result list.
     */
    collectElementsFromPrecedingElements: function (startElement, stencilId) {
        var visitedElements = [];
        var collectedElements = [];

        var incomingShapesIterator = startElement.getIncomingShapes();
        if (incomingShapesIterator) {
            for (var i = 0; i < incomingShapesIterator.length; i++) {
                var incomingShape = incomingShapesIterator[i];
                if (visitedElements.indexOf(incomingShape.id) < 0) {
                    EDITOR.UTIL._visitElementAndCollectElement(incomingShape, stencilId, visitedElements, collectedElements);
                }
            }
        }

        return collectedElements;
    },

    _visitElementAndCollectProperty: function (element, propertyType, visitedElementsArray, collectedProperties) {

        visitedElementsArray.push(element.id);

        var property = element.properties[propertyType]
        if (property) {
            collectedProperties.push(property);
        }

        var incomingShapesIterator = element.getIncomingShapes();
        if (incomingShapesIterator) {
            for (var i = 0; i < incomingShapesIterator.length; i++) {
                var incomingShape = incomingShapesIterator[i];
                if (visitedElementsArray.indexOf(incomingShape.id) < 0) {
                    EDITOR.UTIL._visitElementAndCollectProperty(incomingShape, propertyType, visitedElementsArray, collectedProperties);
                }
            }
        }
    },

    _visitElementAndCollectElement: function (element, stencilId, visitedElementsArray, collectedElements) {

        visitedElementsArray.push(element.id);

        var elementStencilId = element.getStencil().id();
        if (elementStencilId && elementStencilId.indexOf(stencilId) >= 0) {
            collectedElements.push(element);
        }

        var incomingShapesIterator = element.getIncomingShapes();
        if (incomingShapesIterator) {
            for (var i = 0; i < incomingShapesIterator.length; i++) {
                var incomingShape = incomingShapesIterator[i];
                if (visitedElementsArray.indexOf(incomingShape.id) < 0) {
                    EDITOR.UTIL._visitElementAndCollectElement(incomingShape, stencilId, visitedElementsArray, collectedElements);
                }
            }
        }
    },

    /**
     * Goes up the chain of parents of the provided element.
     * When the property is encountered, its value is immediately returned.
     * If the chain of parents is completely walked through, undefined is returned.
     */
    getPropertyFromParent: function (element, propertyType) {
        if (element.parent) {
            return EDITOR.UTIL._getPropertyFromParent(element.parent, propertyType);
        } else {
            return undefined;
        }

    },

    _getPropertyFromParent: function (parentElement, propertyType) {
        var property = parentElement.properties[propertyType];
        if (property) {
            return property;
        }

        if (parentElement.parent) {
            return EDITOR.UTIL._getPropertyFromParent(parentElement.parent, propertyType);
        } else {
            return undefined;
        }
    }

};
