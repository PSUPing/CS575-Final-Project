
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
        @$log.debug "getVirus()"
        deferred = @$q.defer()

        @$http.get("/viruses/" + virMD5)
        .success((data, status, headers) =>
                @$log.info("Successfully retrieved Virus Info for " + virMD5 + " - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to retrieve Virus Info " + virMD5 + " - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

    addVirus: (virus) ->
        @$log.debug "addVirus #{angular.toJson(virus, true)}"
        deferred = @$q.defer()

        @$http.post('/viruses', virus)
        .success((data, status, headers) =>
                @$log.info("Successfully added Virus Info - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to add Virus Info - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

    updateVirus: (virus) ->
        @$log.debug "updateVirus #{angular.toJson(virus, true)}"
        deferred = @$q.defer()

        @$http.put('/viruses', virus)
        .success((data, status, headers) =>
                @$log.info("Successfully updated Virus Info - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to update Virus Info - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

servicesModule.service('VirusService', VirusService)