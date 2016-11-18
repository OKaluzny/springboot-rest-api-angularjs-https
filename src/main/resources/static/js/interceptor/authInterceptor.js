angular.module('myApp')
    .factory('AuthInterceptor', [function () {
        return {
            // Sent the token (if present) with each request
            'request': function (config) {
                config.headers = config.headers || {};
                var encodedString = btoa("user:user");
                config.headers.Authorization = 'Basic ' + encodedString;
                return config;
            }
        };
    }]);

