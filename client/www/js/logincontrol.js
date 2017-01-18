angular.module('hoods.logincontrol', ['ionic'])

.controller('LoginCtrl', function($scope, $location) {
    $scope.go = function(path) {
        $location.path(path);
    };
});
