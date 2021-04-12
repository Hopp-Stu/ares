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

var ActivitiRest = {
	options: {},
	getProcessDefinitionByKey: function(processDefinitionKey, callback) {
		var url = Lang.sub(this.options.processDefinitionByKeyUrl, {processDefinitionKey: processDefinitionKey});

		$.ajax({
			url: url,
			dataType: 'jsonp',
			cache: false,
			async: true,
			success: function(data, textStatus) {
				var processDefinition = data;
				if (!processDefinition) {
					console.error("Process definition '" + processDefinitionKey + "' not found");
				} else {
				  callback.apply({processDefinitionId: processDefinition.id});
				}
			}
		}).done(function(data, textStatus) {
			console.log("ajax done");
		}).fail(function(jqXHR, textStatus, error){
			console.error('Get diagram layout['+processDefinitionKey+'] failure: ', textStatus, 'error: ', error, jqXHR);
		});
	},

	getProcessDefinition: function(processDefinitionId, callback) {
		var url = Lang.sub(this.options.processDefinitionUrl, {processDefinitionId: processDefinitionId});

		$.ajax({
			url: url,
			dataType: 'jsonp',
			cache: false,
			async: true,
			success: function(data, textStatus) {
				var processDefinitionDiagramLayout = data;
				if (!processDefinitionDiagramLayout) {
					console.error("Process definition diagram layout '" + processDefinitionId + "' not found");
					return;
				} else {
					callback.apply({processDefinitionDiagramLayout: processDefinitionDiagramLayout});
				}
			}
		}).done(function(data, textStatus) {
			console.log("ajax done");
		}).fail(function(jqXHR, textStatus, error){
			console.log('Get diagram layout['+processDefinitionId+'] failure: ', textStatus, jqXHR);
		});
	},

	getHighLights: function(processInstanceId, callback) {
		var url = Lang.sub(this.options.processInstanceHighLightsUrl, {processInstanceId: processInstanceId});

		$.ajax({
			url: url,
			dataType: 'jsonp',
			cache: false,
			async: true,
			success: function(data, textStatus) {
				console.log("ajax returned data");
				var highLights = data;
				if (!highLights) {
					console.log("highLights not found");
					return;
				} else {
					callback.apply({highLights: highLights});
				}
			}
		}).done(function(data, textStatus) {
			console.log("ajax done");
		}).fail(function(jqXHR, textStatus, error){
		  console.log('Get HighLights['+processInstanceId+'] failure: ', textStatus, jqXHR);
		});
	}
};
