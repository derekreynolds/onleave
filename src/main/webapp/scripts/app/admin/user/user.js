'use strict';

angular.module('onleaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.user', {
                parent: 'admin',
                url: '/admin/user'
            })
            .state('admin.user.list', {
                url: '/list',
                data: {
                    roles: [], 
                    pageTitle: 'user.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/user/user.list.html',
                        controller: 'UserListController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user');
                        return $translate.refresh();
                    }],
                    users: ['UserService', function(UserService){
                        return UserService.query();
                    }]
                }
            })
            .state('admin.user.show', {                
                url: '/show/:id',
                data: {
                    roles: [], 
                    pageTitle: 'user.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/user/user.show.html',
                        controller: 'UserShowController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user');
                        return $translate.refresh();
                    }],
                    managers: ['ManagerService', function(ManagerService){
                        return ManagerService.getSelectOptions();
                    }]
                    
                }
            })
            .state('admin.user.create', {                
                url: '/create',
                data: {
                    roles: [], 
                    pageTitle: 'user.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/user/user.create.html?h=1',
                        controller: 'UserCreateController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user');
                        return $translate.refresh();
                    }],
                    managers: ['ManagerService', function(ManagerService){
                        return ManagerService.getSelectOptions();
                    }],
                    securityGroups: ['SecurityGroupService', function(SecurityGroupService){
                        return SecurityGroupService.getSelectOptions();
                    }]
                }
            })
            .state('admin.user.edit', {                
                url: '/edit/:id',
                data: {
                    roles: [], 
                    pageTitle: 'user.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/user/user.edit.html',
                        controller: 'UserController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user');
                        return $translate.refresh();
                    }]
                }
            });
    });
