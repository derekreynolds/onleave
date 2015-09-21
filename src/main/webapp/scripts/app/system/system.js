'use strict';

angular.module('onleaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('system', {
                abstract: true,
                parent: 'site'
            });
    });
