
class VirusCtrl

    constructor: ($routeParams, @$log, @VirusService) ->
        @$log.debug "constructing VirusController"
        @viruses = []

        if $routeParams.virMD5?
            @getVirus($routeParams.virMD5) 
        else 
            @getViruses()

    getViruses: () ->
        @$log.debug "getViruses()"

        @VirusService.getViruses()
        .then(
            (data) =>
                @$log.debug "Promise returned #{data.length} Virus Info"
                @viruses = data
            ,
            (error) =>
                @$log.error "Unable to get Virus Info: #{error}"
            )

    getVirus: (virMD5) ->
        @$log.debug "getVirus(virMD5)"

        @VirusService.getVirus(virMD5)
        .then(
            (data) =>
                @$log.debug "Promise returned #{data.length} Virus Info"
                @viruses = data
            ,
            (error) =>
                @$log.error "Unable to get Virus Info: #{error}"
            )

controllersModule.controller('VirusCtrl', VirusCtrl)