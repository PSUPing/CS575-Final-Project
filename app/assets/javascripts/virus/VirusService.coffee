
class VirusService

    @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
    @defaultConfig = { headers: @headers }

    constructor: (@$log, @$http, @$q) ->
        @$log.debug "constructing VirusService"

    getViruses: () ->
        @$log.debug "getViruses()"
        deferred = @$q.defer()

        @$http.get("/viruses")
        .success((data, status, headers) =>
                @$log.info("Successfully retrieved Virus Info - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to retrieve Virus Info - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

    getVirus: (virMD5) ->
        @$log.debug "getViruses()"
        deferred = @$q.defer()

        @$http.get("/virus/" + virMD5)
        .success((data, status, headers) =>
                @$log.info("Successfully retrieved Virus Info - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to retrieve Virus Info - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

    addVirus: (virus) ->
        @$log.debug "createVirus #{angular.toJson(virus, true)}"
        deferred = @$q.defer()

        @$http.post('/virus', virus)
        .success((data, status, headers) =>
                @$log.info("Successfully added Virus Info - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to add Virus Info - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

servicesModule.service('VirusService', VirusService)