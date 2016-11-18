'use strict';

var App = angular.module('myApp',[]);

App.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);
