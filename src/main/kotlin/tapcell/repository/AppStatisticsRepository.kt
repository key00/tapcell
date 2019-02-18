package tapcell.repository

/*
* this is repository class for AppStatistics entity
* */

import tapcell.model.AppStatisticsModel
import java.util.*


interface AppStatisticsRepository{

    fun get(type:Integer,endDate: Date,startDate: Date) : List<AppStatisticsModel>
}