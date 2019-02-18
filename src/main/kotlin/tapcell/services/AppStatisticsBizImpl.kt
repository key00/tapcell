package tapcell.services
/*
*  calss for implement business of project
*  this class implement basic code for get list of AppStatistics and convert them to AppStatisticsModel
* */

import org.springframework.stereotype.Service
import tapcell.model.AppStatisticsModel
import tapcell.repository.AppStatisticsRepository
import java.util.*


@Service
class AppStatisticsServiceImpl (private val appStatisticsRepository: AppStatisticsRepository) : AppStatisticsService {

    override fun getStatisticsForSpecificType(startDate: Long, endDate: Long, type: Integer): List<AppStatisticsModel> {

        var list : List<AppStatisticsModel>

        try
        {
            list = appStatisticsRepository.get(type,Date(startDate),Date(endDate))

        } catch (e : Exception){
            list = LinkedList()
            e.printStackTrace()
        }

        return list
    }


}