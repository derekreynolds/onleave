angular.module('onleaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tracker', {
                parent: 'system',
                url: '/tracker',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'tracker.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/system/tracker/tracker.html',
                        controller: 'TrackerController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tracker');
                        return $translate.refresh();
                    }]
                },
                onEnter: function(Tracker) {
                    Tracker.subscribe();
                },
                onExit: function(Tracker) {
                    Tracker.unsubscribe();
                },
            });
    });
