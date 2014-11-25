package controllers

import org.slf4j.{LoggerFactory, Logger}
import javax.inject.Singleton
import play.api.mvc._
import com.mongodb.casbah.Imports._

/**
 * The Users controllers encapsulates the Rest endpoints and the interaction with the MongoDB, via ReactiveMongo
 * play plugin. This provides a non-blocking driver for mongoDB as well as some useful additions for handling JSon.
 * @see https://github.com/ReactiveMongo/Play-ReactiveMongo
 */
@Singleton
class Viruses extends Controller {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[Viruses])

  def uri = MongoClientURI("mongodb://bwilhelm:CS575@ds051980.mongolab.com:51980/cs575_virus_db")
  def mongoClient =  MongoClient(uri)
  def db = mongoClient("cs575_virus_db")
  def collection = db("virus_info_db")

  // ------------------------------------------ //
  // Using case classes + Json Writes and Reads //
  // ------------------------------------------ //

  def findViruses = Action {
    // let's do our query
    val allDocs = collection.find()

    var output: String = "Count: " + allDocs.size + "\n\n"
    for(doc <- allDocs) {
      output = output + doc + "\n\n"
    }

    Ok(output)
  }

}
