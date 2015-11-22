'use strict';

angular.module('onleaveApp')
    .factory('UserService', function ($resource) {
        return $resource('api/user/:id', {}, {
                'query': {method: 'GET', isArray: false},
                'get': {method: 'GET'}
                
            });
        });
