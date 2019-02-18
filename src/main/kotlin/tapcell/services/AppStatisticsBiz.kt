package tapcell.services

import tapcell.model.AppStatisticsModel

interface AppStatisticsService {


    fun getStatisticsForSpecificType(startDate: Long, endDate: Long, type: Integer): List<AppStatisticsModel>
}