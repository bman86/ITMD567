var app = angular.module('app',[]);

//controller injects service - gets response - throws errors
app.controller('StockController', ['$scope','StockService',
    function ($scope,StockService) {

        //grab all stocks and store in stock variable
        $scope.getAllStocks = function () {
            StockService.getAllStocks()
                .then(function success(response) {
                        $scope.stocks = response.data._embedded.stocks;
                        $scope.message='';
                        $scope.errorMessage = '';
                    },
                    function error (response) {
                        $scope.message='';
                        $scope.errorMessage = 'Error getting stocks!';
                    });
        }

        //grabs stock by id
        $scope.getStockById = function () {
            var id = $scope.stock.id;
            StockService.getStockById($scope.stock.id)
                .then(function success(response) {
                        $scope.stock = response.data;
                        $scope.stock.id = id;
                        $scope.message='';
                        $scope.errorMessage = '';
                    },
                    function error (response) {
                        $scope.message = '';
                        if (response.status === 404){
                            $scope.errorMessage = 'Stock not found!';
                        }
                        else {
                            $scope.errorMessage = "Error getting stock!";
                        }
                    });
        };

        //add a stock
        $scope.insertStock = function () {
            if ($scope.stock != null && $scope.stock.symbl) {
                StockService.insertStock(
                    $scope.stock.symbl,
                    $scope.stock.own,
                    $scope.stock.entryPrice,
                    $scope.stock.up,
                    $scope.stock.target,
                    $scope.stock.time)
                    .then (function success(response){
                            $scope.message = 'Stock added!';
                            $scope.errorMessage = '';
                        },
                        function error(response){
                            $scope.errorMessage = 'Error adding stock!';
                            $scope.message = '';
                        });
            }
            else {
                $scope.errorMessage = 'Please enter a symbol!';
                $scope.message = '';
            }
        }

        //update stock
        $scope.updateStock = function () {
            StockService.updateStock(
                $scope.stock.id,
                $scope.stock.symbl,
                $scope.stock.own,
                $scope.stock.entryPrice,
                $scope.stock.up,
                $scope.stock.target,
                $scope.stock.time)
                .then(function success(response) {
                        $scope.message = 'Stock data updated!';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.errorMessage = 'Error updating stock!';
                        $scope.message = '';
                    });
        }

        //delete stock
        $scope.deleteStockById = function () {
            StockService.deleteStockById($scope.stock.id)
                .then (function success(response) {
                        $scope.message = 'Stock deleted!';
                        $scope.User = null;
                        $scope.errorMessage='';
                    },
                    function error(response) {
                        $scope.errorMessage = 'Error deleting stock!';
                        $scope.message='';
                    });
        }

    }]);



//Angular service methods

app.service('StockService', [ '$http', function($http) {

    //retrieve stocks by id
    this.getStockById = function getStockById(stockId) {
        return $http({
            method : 'GET',
            url : 'stocks/' + stockId
        });
    }


    //add stocks
    this.insertStock = function insertStock(symbl, own, entryPrice, up, target, time) {
        return $http({
            method : 'POST',
            url : 'stocks',
            data : {
                symbl: symbl,
                own: own,
                entryPrice: entryPrice,
                up: up,
                target: target,
                time: time
            }
        });
    }

    //update stock info
    this.updateStock = function updateStock(id, nsymbl, own, entryPrice, up, target, time) {
        return $http({
            method : 'PATCH',
            url : 'stocks/' + id,
            data : {
                symbl: symbl,
                own: own,
                entryPrice: entryPrice,
                up: up,
                target: target,
                time: time
            }
        });
    }

    //delete stock
    this.deleteStockById = function deleteStockById(id) {
        return $http({
            method : 'DELETE',
            url : 'stocks/' + id
        })
    }

    //get all stocks
    this.getAllStocks = function getAllStocks() {
        return $http({
            method : 'GET',
            url : 'localhost:8080/stocks/'
        });
    }
}]);