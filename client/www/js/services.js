angular.module('starter.services', [])

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
});
