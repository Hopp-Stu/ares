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
 *
 * @author Tom Baeyens
 * @author (Javascript) Dmitry Farafonov
 */

var ActivityImpl = function(activityJson){
	this.outgoingTransitions = [];
	this.outgoingTransitions = [];
	this.incomingTransitions = [];
	this.activityBehavior = null;
	this.parent = null;
	this.isScope = false;
	this.isAsync = false;
	this.isExclusive = false;
	this.x = -1;
	this.y = -1;
	this.width = -1;
	this.height = -1;
	this.properties = {};

	//console.log("activityJson: ", activityJson);

	if (activityJson != undefined) {
		this.setId(activityJson.activityId);

		for (var propertyName in activityJson.properties) {
			this.setProperty(propertyName, activityJson.properties[propertyName]);
		}
		//this.setProperty("name", activityJson.activityName);
		//this.setProperty("type", activityJson.activityType);
		this.setX(activityJson.x);
		this.setY(activityJson.y);
		this.setWidth(activityJson.width);
		this.setHeight(activityJson.height);

		if (activityJson.multiInstance)
			this.setProperty("multiInstance", activityJson.multiInstance);
		if (activityJson.collapsed) {
			this.setProperty("collapsed", activityJson.collapsed);
		}
		if (activityJson.isInterrupting != undefined)
			this.setProperty("isInterrupting", activityJson.isInterrupting);
	}
};

ActivityImpl.prototype = {
	outgoingTransitions: [],
	outgoingTransitions: [],
	incomingTransitions: [],
	activityBehavior: null,
	parent: null,
	isScope: false,
	isAsync: false,
	isExclusive: false,

	id: null,

	properties: {},

	// Graphical information
	x: -1,
	y: -1,
	width: -1,
	height: -1,

	setId: function(id){
		this.id = id;
	},

	getId: function(){
		return this.id;
	},


	setProperty: function(name, value){
		this.properties[name] = value;
	},
	getProperty: function(name){
		return this.properties[name];
	},

	createOutgoingTransition: function(transitionId){

	},

	toString: function(id) {
		return "Activity("+id+")";
	},

	getParentActivity: function(){
	/*
		if (parent instanceof ActivityImpl) {
79       return (ActivityImpl) parent;
80     }
81     return null;
	*/
	return this.parent;
	},

	// restricted setters ///////////////////////////////////////////////////////

	setOutgoingTransitions: function(outgoingTransitions){
		this.outgoingTransitions = outgoingTransitions;
	},

	setParent: function(parent){
		this.parent = parent;
	},

	setIncomingTransitions: function(incomingTransitions){
		this.incomingTransitions = incomingTransitions;
	},

	// getters and setters //////////////////////////////////////////////////////

	getOutgoingTransitions: function(){
		return this.outgoingTransitions;
	},

	getActivityBehavior: function(){
		return this.activityBehavior;
	},

	setActivityBehavior: function(activityBehavior){
		this.activityBehavior = activityBehavior;
	},

	getParent: function(){
		return this.parent;
	},

	getIncomingTransitions: function(){
		return this.incomingTransitions;
	},

	isScope: function(){
		return this.isScope;
	},

	setScope: function(isScope){
		this.isScope = isScope;
	},

	getX: function(){
		return this.x;
	},

	setX: function(x){
		this.x = x;
	},

	getY: function(){
		return this.y;
	},

	setY: function(y){
		this.y = y;
	},

	getWidth: function(){
		return this.width;
	},

	setWidth: function(width){
		this.width = width;
	},

	getHeight: function(){
		return this.height;
	},

	setHeight: function(height){
		this.height = height;
	},

  isAsync: function() {
    return this.isAsync;
  },

  setAsync: function(isAsync) {
    this.isAsync = isAsync;
  },

  isExclusive: function() {
    return this.isExclusive;
  },

  setExclusive: function(isExclusive) {
    this.isExclusive = isExclusive;
  },

	vvoid: function(){}
};
