package code.dynamicResourceDoc

import com.openbankproject.commons.model.JsonFieldReName
import net.liftweb.common.Box
import net.liftweb.util.SimpleInjector
import java.net.URLDecoder

import scala.collection.immutable.List

object DynamicResourceDocProvider extends SimpleInjector {

  val provider = new Inject(buildOne _) {}

  def buildOne: MappedDynamicResourceDocProvider.type = MappedDynamicResourceDocProvider
}

case class JsonDynamicResourceDoc(
  dynamicResourceDocId: Option[String],
  connectorMethodBody: String,
  partialFunction: String, 
  implementedInApiVersion: String, 
  partialFunctionName: String, 
  requestVerb: String, 
  requestUrl: String, 
  summary: String, 
  description: String, 
  exampleRequestBody: String, 
  successResponseBody: String,
  errorResponseBodies: String,
  tags: String,
  roles: String,
  isFeatured: Boolean,
  specialInstructions: String,
  specifiedUrl: String
) extends JsonFieldReName{
  def decodedMethodBody: String = URLDecoder.decode(connectorMethodBody, "UTF-8")
}

trait DynamicResourceDocProvider {

  def getById(dynamicResourceDocId: String): Box[JsonDynamicResourceDoc]
  def getAll(): List[JsonDynamicResourceDoc]

  def create(entity: JsonDynamicResourceDoc): Box[JsonDynamicResourceDoc]
  def update(entity: JsonDynamicResourceDoc): Box[JsonDynamicResourceDoc]
  def deleteById(dynamicResourceDocId: String): Box[Boolean]

}
