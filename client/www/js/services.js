angular.module('starter.services', ['btford.socket-io'])

.service('LoginService', function($q) {
    return {
        loginUser: function(name, pw) {
            var deferred = $q.defer();
            var promise = deferred.promise; // Något som behövs för async-calls vilket är ett krav vid REST. Läs på vid behov!

            if (name == 'user' && pw == 'secret') {
                deferred.resolve('Welcome ' + name + '!');
            } else {
                deferred.reject('Wrong credentials');
            }
            promise.success = function(fn) {
                promise.then(fn);
                return promise;
            }
            promise.error = function(fn) {
                promise.then(null, fn);
                return promise;
            }
            return promise;
        }
    }
})

.factory('socket', function(socketFactory) {
    var myIoSocket = io.connect('http://ec2-34-196-203-68.compute-1.amazonaws.com:8000');
    mySocket = socketFactory({
        ioSocket: myIoSocket
    });
    return mySocket;
})

.service('ClientUtilService',function(){
	var name = "";

	return {	
		getUserCredentials : function(){		
		return 	this.name
		},	
		setUserCredentials : function(username){
			this.name = username; 
}
}
})


.service('JSONCleaner',function(){

    

        return {
            cleanJSON : function(json){
                json.replace(/\\/g, '');
                console.log(json);
            }
        }
});
