'use strict';

angular.module('onleaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.calendar', {
                parent: 'admin',
                url: '/admin/calendar'
            })
            .state('admin.calendar.list', {
                url: '/list',
                data: {
                    roles: [], 
                    pageTitle: 'calendar.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/calendar/calendar.list.html',
                        controller: 'CalendarController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('calendar');
                        return $translate.refresh();
                    }]
                }
            })
            .state('admin.calendar.show', {                
                url: '/show/:id',
                data: {
                    roles: [], 
                    pageTitle: 'calendar.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/calendar/calendar.show.html',
                        controller: 'CalendarController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('calendar');
                        return $translate.refresh();
                    }]                  
                }
            })
            .state('admin.calendar.create', {                
                url: '/create',
                data: {
                    roles: [], 
                    pageTitle: 'calendar.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/calendar/calendar.create.html'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('calendar');
                        return $translate.refresh();
                    }]
                }
            })
            .state('admin.calendar.edit', {                
                url: '/edit/:id',
                data: {
                    roles: [], 
                    pageTitle: 'calendar.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/calendar/calendar.edit.html'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('calendar');
                        return $translate.refresh();
                    }]
                }
            });
    });
