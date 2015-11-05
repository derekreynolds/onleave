'use strict';

angular.module('onleaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('request', {
                abstract: true,
                parent: 'site'
            });
    });
