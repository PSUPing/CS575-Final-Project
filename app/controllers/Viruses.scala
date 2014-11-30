package controllers

import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import scala.concurrent.Future
import reactivemongo.api.Cursor
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import org.slf4j.{LoggerFactory, Logger}
import javax.inject.Singleton
import play.api.mvc._
import play.api.libs.json._

/*import org.slf4j.{LoggerFactory, Logger}
import javax.inject.Singleton
import play.api.mvc._
import com.mongodb.casbah.Imports._
*/
/**
 * The Users controllers encapsulates the Rest endpoints and the interaction with the MongoDB, via ReactiveMongo
 * play plugin. This provides a non-blocking driver for mongoDB as well as some useful additions for handling JSon.
 * @see https://github.com/ReactiveMongo/Play-ReactiveMongo
 */
@Singleton
class Viruses extends Controller with MongoController {
  import models._
  import models.JsonFormats._

  private final val logger: Logger = LoggerFactory.getLogger(classOf[Virus])

  /*
   * Get a JSONCollection (a Collection implementation that is designed to work
   * with JsObject, Reads and Writes.)
   * Note that the `collection` is not a `val`, but a `def`. We do _not_ store
   * the collection reference to avoid potential problems in development with
   * Play hot-reloading.
   */
  def collection: JSONCollection = db.collection[JSONCollection]("virus_info_db")

  def addVirus = Action.async(parse.json) {
    request =>
    /*
     * request.body is a JsValue.
     * There is an implicit Writes that turns this JsValue as a JsObject,
     * so you can call insert() with this JsValue.
     * (insert() takes a JsObject as parameter, or anything that can be
     * turned into a JsObject using a Writes.)
     */
      request.body.validate[Virus].map {
        virus =>
          collection.insert(virus).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"Virus Info Added")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

  def updateVirus = Action.async(parse.json) {
    request =>
      /*
       * request.body is a JsValue.
       * There is an implicit Writes that turns this JsValue as a JsObject,
       * so you can call insert() with this JsValue.
       * (insert() takes a JsObject as parameter, or anything that can be
       * turned into a JsObject using a Writes.)
       */
      request.body.validate[Virus].map {
        virus: Virus =>
          collection.update(Json.obj("MD5" -> virus.MD5), virus).map {
            lastError =>
              logger.debug(s"Successfully updated with LastError: $lastError")
              Created(s"Virus Info Updated")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

/*  def getVirusForUpdate(virusMD5: String) = Action.async {
    // let's do our query
    val cursor: Cursor[Virus] = collection
      .find(Json.obj("MD5" -> virusMD5))
      .cursor[Virus]

    val futureVirusesList: Future[List[Virus]] = cursor.collect[List]()

    val futureVirusesJsonArray: Future[JsArray] = futureVirusesList.map { viruses =>
      Json.arr(viruses)
    }

    futureVirusesJsonArray.map {
      viruses =>
        Ok(viruses(0))
    }
  }*/
  
  def getVirus(virusMD5: String) = Action.async {
    // let's do our query
    val cursor: Cursor[Virus] = collection
        .find(Json.obj("MD5" -> virusMD5))
        .cursor[Virus]

    val futureVirusesList: Future[List[Virus]] = cursor.collect[List]()

    val futureVirusesJsonArray: Future[JsArray] = futureVirusesList.map { viruses =>
        Json.arr(viruses)
    }

    futureVirusesJsonArray.map {
        viruses =>
            Ok(viruses(0))
    }
  }

  def getViruses = Action.async {
    // let's do our query
    val cursor: Cursor[Virus] = collection
        .find(Json.obj())
        .cursor[Virus]

    val futureVirusesList: Future[List[Virus]] = cursor.collect[List]()

    val futureVirusesJsonArray: Future[JsArray] = futureVirusesList.map { viruses => 
        Json.arr(viruses)
    }

    futureVirusesJsonArray.map {
        viruses =>
            Ok(viruses(0))
    }
  }

}