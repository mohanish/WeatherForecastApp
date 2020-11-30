package com.weatherforecastapp.box.appmodels.weather

import androidx.annotation.Keep
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.weatherforecastapp.box.apphelper.Converters

@Entity(tableName = "weatherforecast")
@Keep
data class WeatherData @JvmOverloads constructor(
    @ColumnInfo(name = "_mid") @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @Embedded @SerializedName("city") var city: City?,
    @SerializedName("cnt") var cnt: Int, // 40
    @SerializedName("cod") var cod: String, // 200
    @SerializedName("list") var list: List<WeatherDetails>?,
    @SerializedName("message") var message: Int // 0
) {
//    constructor() : this(0, null, 0, "", listOf(), 0)
}

@Keep
data class City @JvmOverloads constructor(
    @Embedded @SerializedName("coord") var coord: Coord? = Coord(),
    @SerializedName("country") var country: String = "", // GB
    @ColumnInfo(name = "_cid") @SerializedName("id") var id: Int = 0, // 2643743
    @SerializedName("name") var name: String = "", // London
    @SerializedName("sunrise") var sunrise: Int = 0, // 1578384285
    @SerializedName("sunset") var sunset: Int = 0, // 1578413272
    @SerializedName("timezone") var timezone: Int = 0 // 0
) {
//    constructor() : this(null, "", 0, "", 0, 0, 0)
}

@Keep
data class Coord @JvmOverloads constructor(
    @ColumnInfo(name = "latitude") @SerializedName("lat") var latitude: Double = 0.0, // 51.5073
    @ColumnInfo(name = "longitude") @SerializedName("lon") var longitude: Double = 0.0 // -0.1277
) {
//    constructor() : this(0.0, 0.0)
}

@Keep
data class WeatherDetails @JvmOverloads constructor(
    @Embedded @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("dt") var dt: Int = 0, // 1596564000
    @SerializedName("dt_txt") var dtTxt: String = "", // 2020-08-04 18:00:00
    @Embedded(prefix = "_wd") @SerializedName("main") var main: Main? = Main(),
    @SerializedName("pop") var pop: Double = 0.0, // 0.49
    @Embedded @SerializedName("rain") var rain: Rain? = Rain(),
    @Embedded @SerializedName("sys") var sys: Sys? = Sys(),
    @SerializedName("visibility") var visibility: Int = 0, // 10000
    @Ignore @TypeConverters(Converters::class) @Embedded @SerializedName("weather") var weather: List<Weather>? = listOf(),
    @Embedded @SerializedName("wind") var wind: Wind? = Wind()
) {
//    constructor() : this(null, 0, "", null, 0.0, null, null, 0, listOf(), null)
}

@Keep
data class Clouds @JvmOverloads constructor(
    @SerializedName("all") var all: Int = 0 // 38
) {
//    constructor() : this(0)
}

@Keep
data class Main @JvmOverloads constructor(
    @SerializedName("feels_like") var feelsLike: Double = 0.0, // 293.13
    @SerializedName("grnd_level") var grndLevel: Int = 0, // 976
    @SerializedName("humidity") var humidity: Int = 0, // 84
    @SerializedName("pressure") var pressure: Int = 0, // 1013
    @SerializedName("sea_level") var seaLevel: Int = 0, // 1013
    @SerializedName("temp") var temp: Double = 0.0, // 293.55
    @SerializedName("temp_kf") var tempKf: Double = 0.0, // -0.5
    @SerializedName("temp_max") var tempMax: Double = 0.0, // 294.05
    @SerializedName("temp_min") var tempMin: Double = 0.0 // 293.55
) {
//    constructor() : this(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0)
}

@Keep
data class Rain @JvmOverloads constructor(
    @SerializedName("3h") var h: Double = 0.0 // 0.53
) {
//    constructor() : this(0.0)
}

@Keep
data class Sys @JvmOverloads constructor(
    @SerializedName("pod") var pod: String = "" // d
) {
//    constructor() : this("")
}

@Keep
data class Weather @JvmOverloads constructor(
    @SerializedName("description") var description: String = "", // light rain
    @SerializedName("icon") var icon: String = "", // 10d
    @ColumnInfo(name = "_wid") @SerializedName("id") var id: Int = 0, // 500
    @ColumnInfo(name = "_wmain") @SerializedName("main") var main: String = "" // Rain
) {
//    constructor() : this("", "", 0, "")
}

@Keep
data class Wind @JvmOverloads constructor(
    @SerializedName("deg") var deg: Int = 0, // 309
    @SerializedName("speed") var speed: Double = 0.0 // 4.35
) {
//    constructor() : this(0, 0.0)
}
