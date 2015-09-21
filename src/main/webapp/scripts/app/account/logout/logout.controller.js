'use strict';

angular.module('onleaveApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
