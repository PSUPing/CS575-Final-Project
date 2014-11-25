package controllers

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import org.slf4j.{LoggerFactory, Logger}
import javax.inject.Singleton
import play.api.mvc._
import play.api.libs.json._
import com.mongodb.casbah.Imports._

/**
 * The Users controllers encapsulates the Rest endpoints and the interaction with the MongoDB, via ReactiveMongo
 * play plugin. This provides a non-blocking driver for mongoDB as well as some useful additions for handling JSon.
 * @see https://github.com/ReactiveMongo/Play-ReactiveMongo
 */
@Singleton
class Viruses extends Controller {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[Viruses])

  /*
   * Get a JSONCollection (a Collection implementation that is designed to work
   * with JsObject, Reads and Writes.)
   * Note that the `collection` is not a `val`, but a `def`. We do _not_ store
   * the collection reference to avoid potential problems in development with
   * Play hot-reloading.
   */
  def uri = MongoClientURI("mongodb://bwilhelm:CS575@ds051980.mongolab.com:51980/cs575_virus_db")
  def mongoClient =  MongoClient(uri)
  def db = mongoClient("cs575_virus_db")
  def collection = db("virus_info_db")

  // ------------------------------------------ //
  // Using case classes + Json Writes and Reads //
  // ------------------------------------------ //

  import models._
  import models.JsonFormats._

//  def createVirus = Action.async(parse.json) {
//    request =>
//    /*
//     * request.body is a JsValue.
//     * There is an implicit Writes that turns this JsValue as a JsObject,
//     * so you can call insert() with this JsValue.
//     * (insert() takes a JsObject as parameter, or anything that can be
//     * turned into a JsObject using a Writes.)
//     */
//      request.body.validate[Virus].map {
//        virus =>
//        // `virus` is an instance of the case class `models.Virus`
//          collection.insert(virus).map {
//            lastError =>
//              logger.debug(s"Successfully inserted with LastError: $lastError")
//              Created(s"Virus Created")
//          }
//      }.getOrElse(Future.successful(BadRequest("invalid json")))
//  }

  def findViruses = Action.async {
    // let's do our query
    val allDocs = collection.find()

    // Print results
    println( allDocs )
    for(doc <- allDocs) println(doc)

    // gather all the JsObjects in a list
    val futureVirusesList: Future[List[MongoDBObject]] = Future { allDocs.toList }

    // everything's ok! Let's reply with the array
    futureVirusesList.map {
      viruses =>
        Ok(viruses(0))
    }
  }

}
