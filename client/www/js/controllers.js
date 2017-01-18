angular.module('hoods.controllers', ['ionic'])

.controller('LoginCtrl', function($scope) {
    $scope.userName = "";
})

.controller('CharCtrl', function($scope) {
    $scope.userName = "Sir Bengt";
    $scope.currentHP = 77;
    $scope.maxHP = 100;
    $scope.str = 666;
    $scope.dex = 1337;
})

.controller('TabsCtrl', function($scope) {

});
