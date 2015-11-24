/*jshint bitwise: false*/
'use strict';

angular.module('onleaveApp')
	.factory('SelectOptionService', ['$rootScope', function($rootScope) {

	 var factory = {}; 

	 factory.setOptionId = function(obj, propertyName) {

	 	obj[propertyName + 'Id'] = obj[propertyName].key;

	 	delete obj[propertyName];
	 };

	 return factory;

}]);