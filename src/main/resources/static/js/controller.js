app.controller('linkedinController', ['$scope','$log', '$http', '$location', '$route', function ($scope,$log, $http, $location, $route) {
    $scope.headingTitle = "Insert Linkedin profile";
    $scope.test = "Result not processed";
    $scope.change = function () {
        $scope.show = false;
        $scope.error = false;
        $scope.inexistent = false;
        $scope.fileIsEmpty = false;
        $scope.incorectTitles = false;
    }
    $scope.uploadFile = function () {
        $scope.change();
        var file = $scope.myFile;
        var uploadUrl = "/uploadLinkedin";
        if (file != null) {
            var fd = new FormData();
            fd.append('file', file);
            $scope.myData = "";
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
                .success(function (data) {
                    $log.log(data);
                    $scope.result = data;
                    $scope.test = "Result set is ready";
                })
                .error(function (data) {
                    $log.log(data);
                    $scope.result = data;
                    $scope.test = "Result set is ready";
                });
        }
        else {
            $scope.fileIsEmpty = true;
        }
    }
}]);

app.controller('curriculumController', ['$scope','$log', '$http', '$location', '$route', function ($scope,$log, $http, $location, $route){
    $scope.headingTitle = "Insert CV profile";
    $scope.test = "Result not processed";
    $scope.uploadFile = function () {
        var file = $scope.myFile;
        var uploadUrl = "/uploadCV";
        if (file != null) {
            var fd = new FormData();
            fd.append('file', file);
            $scope.myData = "";
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
                .success(function (data) {
                    $log.log(data);
                    $scope.result1 = data;
                    $scope.test = "Result set is ready";
                })
                .error(function (data) {
                    $log.log(data);
                    $scope.result1 = data;
                    $scope.test = "Result set is ready";
                });
        }
        else {
            $scope.fileIsEmpty = true;
        }
    }
}]);

app.controller('contactusController', function($scope) {
    $scope.headingTitle = "Contributors";
});
