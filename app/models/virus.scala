package models

case class Virus(MD5: String,
                 SHA1: String,
                 SHA256: String,
                 SSDeep: String,
                 TimeStamp: String,
                 AntiVirusDetection: Map[String,String],
                 FileInformation: Map[String,String])

object JsonFormats {
  import play.api.libs.json.Json

  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val userFormat = Json.format[Virus]
}