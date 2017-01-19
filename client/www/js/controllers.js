angular.module('starter.controllers', [])

.controller('LoginCtrl', function($scope, LoginService, $ionicPopup, $state) {
    $scope.data = {};

    $scope.login = function() {
        LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
            $state.go('tab.chat');
        }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });
    }
})

.controller('ChatCtrl', function($scope) {

})

.controller('StatsCtrl', function($scope) {
    $scope.player = {
        title: 'Sir',
        name: 'Bengt',
        currentHp: '73',
        maxHp: '100',
        strength: '666',
        vitality: '1337',
        dexterity: '-37'
    };
})

.controller('InventoryCtrl', function($scope) {

})

.controller('NearbyCtrl', function($scope) {

});
