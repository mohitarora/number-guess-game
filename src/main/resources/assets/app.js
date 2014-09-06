var app = angular.module('game', []);

app.controller('GameController', function ($scope, $http, $timeout, LS) {

    this.numGuess = '';
    this.flash = '';
    this.trialCount = 5 - LS.getData();

    this.setState = function (newState) {
        this.state = newState;
    };

    this.setFlash = function (newflash) {
        this.flash = newflash;
    };

    this.updateCount = function () {
        LS.setData(1 + parseInt(LS.getData() | 0));
        this.trialCount = 5 - LS.getData();
    };

    this.reset = function () {
        $timeout(function() {
            $scope.game.flash = '';
            LS.setData(0);
            $scope.game.trialCount = 5 - LS.getData();
            $scope.game.numGuess = '';
        }, 1000);
    };

    this.guess = function () {
        $http({
            method: 'GET',
            url: '/api/result',
            params: { number: this.numGuess }
        }).success(function (result) {
            $scope.game.setFlash('Congratulations you cracked it! You can play again');
            $scope.game.reset();
        }).error(function (result) {
            $scope.game.setFlash('Wrong answer! Please try again.');
            $scope.game.updateCount();
        });
    };
});

app.factory("LS", function ($window, $rootScope) {
    return {
        setData: function (val) {
            $window.localStorage && $window.localStorage.setItem('my-storage', val);
            return this;
        },
        getData: function () {
            return $window.localStorage && $window.localStorage.getItem('my-storage');
        }
    };
});
