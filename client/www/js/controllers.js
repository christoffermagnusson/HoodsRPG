angular.module('starter.controllers', ['ngCordova'])

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

.controller('NearbyCtrl', function($scope, $ionicPopup, $cordovaGeolocation) {

    var posOptions = {timeout: 10000, enableHighAccuracy: false};
    $scope.lat = 1;
    $scope.lng = 3;

    $cordovaGeolocation
    .getCurrentPosition(posOptions)

    .then(function (position) {
      $scope.lat = position.coords.latitude
      $scope.lng = position.coords.latitude
    }, function(err) {
        console.log(err)
        var alertPopup = $ionicPopup.alert({
            title: 'Error',
            template: err
        })
    });

    var watchOptions = {timeout: 3000, enableHighAccuracy: false};
    var watch = $cordovaGeolocation.watchPosition(watchOptions);

    watch.then(
        null,

        function(err) {
          console.log(err)
            var alertPopup = $ionicPopup.alert({
                title: 'Error',
                template: err
            })
        },

        function(position) {
            $scope.lat = position.coords.latitude
            $scope.lng = position.coords.longitude
        }
    );

    watch.clearWatch();
});
