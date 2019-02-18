package tapcell.controller


import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tapcell.model.AppStatisticsModel
import tapcell.services.AppStatisticsService

@RestController
class Controller (private val appStatisticsService: AppStatisticsService) {


    @Cacheable(value = "AppStaticModel")
    @GetMapping("/getStats")
    fun getStats(@RequestParam(value = "startDate") startDate: Long,
                 @RequestParam(value = "endDate")   endDate: Long,
                 @RequestParam(value = "type")     type:Integer): List<AppStatisticsModel> {
        return appStatisticsService.getStatisticsForSpecificType(startDate,endDate,type)
    }
}