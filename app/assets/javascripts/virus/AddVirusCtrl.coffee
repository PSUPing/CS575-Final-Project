
class AddVirusCtrl

  constructor: (@$log, @$location,  @VirusService) ->
    @$log.debug "constructing AddVirusController"
    @virus = {}

  addVirus: () ->
    @$log.debug "addVirus()"
    @VirusService.addVirus(@virus)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data} Virus"
        @virus = data
        @$location.path("/")
    ,
      (error) =>
        @$log.error "Unable to add Virus: #{error}"
    )

controllersModule.controller('AddVirusCtrl', AddVirusCtrl)