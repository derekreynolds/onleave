'use strict';

angular.module('onleaveApp')
	.controller('UserListController', function($scope, $state) {

		$scope.users = {content: [{id: 1, email: 'jeff.hanneman@slayer.com', firstName: 'Jeff', lastName: 'Hanneman', roles: ['ROLE_SYS_ADMIN']}, 
					{id: 2, email: 'dave.lombardo@slayer.com',firstName: 'Dave', lastName: 'Lombardo', roles: ['ROLE_ADMIN']}, 
					{id: 3, email: 'kerry.king@slayer.com',firstName: 'Kerry', lastName: 'King', roles: ['ROLE_MANAGER']},
					{id: 4, email: 'tom.arraya@slayer.com',firstName: 'Tom', lastName: 'Arraya', roles: ['ROLE_USER']}]};		

		$scope.show = function() {
			$state.go('admin.user.show', {id: 1});
		};

		$scope.decorateUsers = function() {
			_.each($scope.users.content, function(user) {				
				if(_.includes(user.roles, 'ROLE_SYS_ADMIN')) {
					user.roleCssClass = 'user-type-system-admin';
				} else if(_.includes(user.roles, 'ROLE_ADMIN')) {
					user.roleCssClass = 'user-type-admin';
				} else if(_.includes(user.roles, 'ROLE_MANAGER')) {
					user.roleCssClass = 'user-type-manager';
				} else {
					user.roleCssClass = 'user-type-user';
				}
			});
		};

		$scope.decorateUsers();

	})
	.controller('UserController', function($scope, $state) {

		$scope.user = {id: 1, email: 'jeff.hanneman@slayer.com', firstName: 'Jeff', lastName: 'Hanneman', roles: ['ROLE_SYS_ADMIN']};		


	})
	;