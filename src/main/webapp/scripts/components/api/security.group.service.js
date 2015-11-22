'use strict';

angular.module('onleaveApp')
	.factory('SecurityGroupService', function ($resource) {
        return $resource('api/v1.0/security/group', {}, {
            'getSelectOptions': { 
            	method: 'GET', 
            	isArray: true,
            	url: 'api/v1.0/security/group/selectoption'
            }
        });
    });