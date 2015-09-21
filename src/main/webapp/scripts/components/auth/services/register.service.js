'use strict';

angular.module('onleaveApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


