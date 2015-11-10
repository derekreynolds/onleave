'use strict';

angular.module('onleaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('request.annual', {
                parent: 'request',
                url: '/request/annual',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'annual.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/request/annual/annual.list.html',
                        controller: 'AnnualController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('annual');
                        return $translate.refresh();
                    }]
                }
            });
    });