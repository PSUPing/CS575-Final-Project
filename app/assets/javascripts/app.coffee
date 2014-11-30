
dependencies = [
    'ngRoute',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.services',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'myApp.routeConfig'
]

app = angular.module('myApp', dependencies)

angular.module('myApp.routeConfig', ['ngRoute'])
    .config ($routeProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/assets/partials/viruses.html'
            })
            .when('/debug', {
                templateUrl: '/assets/partials/viruses.html'
            })
            .when('/virus/add', {
                templateUrl: '/assets/partials/add.html'
            })
            .when('/virus/update/:virMD5', {
                templateUrl: '/assets/partials/update.html'
            })
            .when('/virus/:virMD5', {
                templateUrl: '/assets/partials/virus.html'
            })
            .otherwise({redirectTo: '/'})

@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])