angular.module('starter.controllers', ['ngCordova', 'btford.socket-io'])

.controller('LoginCtrl', function($scope, LoginService, ClientUtilService, $ionicPopup, $state, socket) {
    $scope.data = {};

    $scope.login = function() {
        LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
            
            // SOCKET IO TEST
            socket.on('HoodsEvent', function() {
                $state.go('tab.chat');
            });

            var credentials = {
                command: 'REQUESTLOGIN',
                params: {
                    username: $scope.data.username,
                    password: $scope.data.password
                }
            };


            socket.emit('JSONEvent', JSON.stringify(credentials));

           ClientUtilService.setUserCredentials($scope.data.username)
        }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });
    }
})

.controller('ChatCtrl', function($scope,ClientUtilService,socket) { // måste ha någont typ av eventhantering,,
	$scope.data = {};
	$scope.data.username = ClientUtilService.getUserCredentials();	

	$scope.onEnterButton = function(){
	
	var chatInput = {
		command : 'CHATMESSAGE',
		params : {
			username: $scope.data.username,
			chatMessage: $scope.data.input
			}
	};

	socket.emit('JSONEvent', JSON.stringify(chatInput));
	
	socket.on('HoodsEvent', function(event){
		var chatEvent = JSON.parse(event.rawData); // parse chatmessage from server to JSON
		
		console.log("EVENT RECEIVED : \n"+JSON.stringify(event));
		
		$scope.data.output = chatEvent.params.username + ' > '+ chatEvent.params.chatMessage +'\n';	
	});			
	};
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

    var posOptions = {timeout: 10000, enableHighAccuracy: true};
    $cordovaGeolocation
    .getCurrentPosition(posOptions)

    .then(function (position) { // Will wait for user at permission prompt
      $scope.lat = position.coords.latitude
      $scope.lng = position.coords.longitude

      var watchOptions = {timeout: 3000, enableHighAccuracy: true};
      var watch = $cordovaGeolocation.watchPosition(watchOptions);

      watch.then( // If initial position request succeeded > Keep watching!
          null,
          function(err) { // Watch error handling
              console.log(err)
          },
          function(position) {
              $scope.lat = position.coords.latitude;
              $scope.lng = position.coords.longitude;
          }
      );
    }, function(err) { // Will throw "Illegal access" if permissions are denied
        console.log(err)
    });
});
