
class UpdateVirusCtrl

  constructor: ($routeParams, @$log, @$location,  @VirusService) ->
    @$log.debug "constructing UpdateVirusController"
    @virus = {}

    if $routeParams.virMD5?
      @getVirus($routeParams.virMD5)
    else
      @updateVirus()

  getVirus: (virMD5) ->
    @$log.debug "getVirus(virMD5)"

    @VirusService.getVirus(virMD5)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data.length} Virus Info"
        @virus = data[0]
    ,
      (error) =>
        @$log.error "Unable to get Virus Info: #{error}"
    )

  updateVirus: () ->
    @$log.debug "updateVirus()"
    @redirRoute = "/virus/" + @virus.MD5 # This gets lost after the update runs
    @VirusService.updateVirus(@virus)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data} Virus"
        @virus = data
        @$location.path(@redirRoute)
    ,
      (error) =>
        @$log.error "Unable to update Virus: #{error}"
    )

controllersModule.controller('UpdateVirusCtrl', UpdateVirusCtrl)