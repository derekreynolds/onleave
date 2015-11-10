'use strict';

angular.module('onleaveApp')
	.factory('ManagerService', function ($resource) {
        return $resource('api/v1.0/manager', {}, {
            'getSelectOptions': { 
            	method: 'GET', 
            	isArray: true,
            	url: 'api/v1.0/manager/selectoption'
            }
        });
    });