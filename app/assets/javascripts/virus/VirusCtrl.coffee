
class VirusCtrl

    constructor: (@$log, @VirusService) ->
        @$log.debug "constructing VirusController"
        @viruses = []
        @getAllViruses()

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


controllersModule.controller('VirusCtrl', VirusCtrl)