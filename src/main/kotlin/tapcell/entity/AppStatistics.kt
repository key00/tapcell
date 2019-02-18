package tapcell.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.util.*

@Document (collection = "app_statistics")
class AppStatistics  (@Id val id: ObjectId, @Field val reportTime: Date,
                      @Field val type: Int, @Field val videoRequests: Int,
                      @Field val webViewRequests: Int,@Field val videoClicks: Int,
                      @Field val webviewClicks: Int, @Field val videoInstalls: Int,
                      @Field val webviewInstalls: Int) : Serializable