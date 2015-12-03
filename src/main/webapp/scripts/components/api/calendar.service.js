'use strict';

angular.module('onleaveApp')
    .factory('CalendarService', function ($resource) {
        return $resource('api/v1.0/holidaycalendar/:id', {}, {
                'query': {method: 'GET', isArray: false},
                'get': {method: 'GET'}
                
            });
        });