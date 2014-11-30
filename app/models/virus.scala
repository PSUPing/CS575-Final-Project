package models

case class Virus(MD5: String,
                 SHA1: Option[String],
                 SHA256: Option[String],
                 SSDeep: Option[String],
                 time_stamp: Option[String],
                 av_scans: Option[VirusScan],
                 file_information: Option[FileInfo])

case class VirusScan(AVG: Option[String],
                     Avast: Option[String],
                     Kaspersky: Option[String],
                     Microsoft: Option[String],
                     Sophos: Option[String],
                     AVware: Option[String],
                     AdAware: Option[String],
                     BaiduInternational: Option[String],
                     BitDefender: Option[String],
                     ClamAV: Option[String],
                     ESETNOD32: Option[String],
                     Emsisoft: Option[String],
                     McAfee: Option[String],
                     McAfeeGWEdition: Option[String],
                     Symantec: Option[String],
                     VBA32: Option[String],
                     nProtect: Option[String])
    
case class FileInfo(size: Option[Int],
                    file_type: Option[String],
                    mime_type: Option[String],
                    description: Option[String],
                    code_size: Option[Int])

object JsonFormats {
  import play.api.libs.json.Json

  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val fileInfoFormat = Json.format[FileInfo]
  implicit val virusScanFormat = Json.format[VirusScan]
  implicit val virusFormat = Json.format[Virus]
}