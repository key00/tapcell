package tapcell.repository

/*
* this is repository class for AppStatistics entity
* */

import org.springframework.data.mongodb.core.MongoTemplate
import tapcell.entity.AppStatistics
import java.util.*

import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import tapcell.model.AppStatisticsModel






@Service
class AppStatisticsRepositoryImpl (var mongoTemplate: MongoTemplate):AppStatisticsRepository{


    val yearMilliseconds = 31556952000
    val weakMilliseconds = 604800000
    val baseYearMilliseconds = getFirstPersianTime()
    var baseYear =1395


    override fun get(type: Integer, startDate: Date, endDate: Date): List<AppStatisticsModel> {

        // usee mongoDb to load specific AppStatistics but dont use aggregation in mongoDb
        val filterStates = match(Criteria("type").`is`(type))
        val filterDate = match(Criteria("reportTime").gt(startDate).lt(endDate))
        val aggregation = newAggregation(filterStates,filterDate)
        val result = mongoTemplate
                .aggregate(aggregation, "app_statistics", AppStatistics::class.java)
        var list : List<AppStatistics> = result.mappedResults

        // use mongo grouping by with lambda to aggregate and grouping by AppStatistics
        val grouped = list.groupingBy{  Pair( getYear(it), getWeek(it))}.aggregate { _, acc: AppStatisticsModel?, e, _ ->
            AppStatisticsModel(
                    getWeek(e),
                    getYear(e),
                    (acc?.requests ?: 0) + e.videoRequests + e.webViewRequests,
                    (acc?.clicks ?: 0) + e.videoClicks + e.webviewClicks,
                    (acc?.installs ?: 0) + e.videoInstalls + e.webviewInstalls)
        }.toSortedMap(kotlin.Comparator{o2,o1 -> o2.first.compareTo(o1.first) or  o2.second.compareTo(o1.second) })

        return grouped.values.toList()
    }

    private fun getWeek(app: AppStatistics) = app.reportTime.time.minus(baseYearMilliseconds).mod(yearMilliseconds).div(weakMilliseconds).plus(1).toInt()

    private fun getYear(app: AppStatistics) = app.reportTime.time.minus(baseYearMilliseconds).div(yearMilliseconds).plus(baseYear).toInt()

    private fun getFirstPersianTime() = Date("2016/03/20").time

}