//import Operator._
//import Parser._
//import Util._
//import org.apache.spark.rdd.RDD
//import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.log4j.Logger
//import org.apache.log4j.Level
//import scala.io.Source
//import scala.util.control.Breaks._
//
///**
//  * Created by Gon on 2016-06-19.
//  */
//
//object SWRL_Reasoner {
//  type Triple = (String, String, String)
//  Logger.getLogger("org").setLevel(Level.WARN)
//  Logger.getLogger("akka").setLevel(Level.WARN)
//
//  def main(args: Array[String]): Unit = {
//    val sparkConf = new SparkConf()//.setAppName("test").setMaster("local[*]")
//    val sc = new SparkContext(sparkConf)
////    System.setProperty("hadoop.home.dir", "c:\\winutil\\")
//
////    val parsedRules = Source.fromFile("RuleSet/test.txt").getLines().toList.map(RuleParser)
//            val parsedRules = Source.fromFile(args(0)).getLines().toList.map(RuleParser)
//
//    val predicateMap = Create_PredicateMap(parsedRules)
//
////    val triples: RDD[Triple] = sc.textFile("Data/test.txt").map(parseTriple)
//            val triples: RDD[Triple] = sc.textFile(args(1)).map(parseTriple)
//
//    var predicateRDD: Array[RDD[Triple]] = Create_PredicateRDD(triples, predicateMap)
//
//
//    val rule_size = parsedRules.size
//
//    var conditionRDD: Array[RDD[List[Triple]]] = new Array[RDD[List[Triple]]](rule_size)
//    var oldConditionRDD: Array[RDD[List[Triple]]] = new Array[RDD[List[Triple]]](rule_size)
//
//    for (a <- 0 to (rule_size - 1)) {
//      oldConditionRDD(a) = sc.emptyRDD
//    }
//
//    var conclusionRDD: Array[RDD[Triple]] = new Array[RDD[Triple]](rule_size)
//
//
//    //***********************Reasoning Start!***********************
//    var deliberateCount = -1
//    var inferredCount:Long = 0
//    var phaseNum = 1
//
//    val totalTime = time {
//      inferredCount = 0
//
//      while (deliberateCount != 0) {
//        deliberateCount = 0
//        var rule_number = 0
//
//        println()
//        println("********Phase" + phaseNum + "********")
//        println()
//
//        phaseNum += 1
//        for (rule <- parsedRules) {
//          val (currentConditionRDD, condition) = Create_ConditionRDD(sc, rule, predicateMap, predicateRDD)
//
////          println("=======================================")
//          println("Rule Number : " + rule_number)
//          print("Deliberation Time : ")
//          var conditionRDD_Count: Long = 0
//          val deliberationTime = time {
//            conditionRDD(rule_number) = currentConditionRDD.subtract(oldConditionRDD(rule_number)).repartition(4)
//            oldConditionRDD(rule_number) = oldConditionRDD(rule_number).union(currentConditionRDD).distinct().repartition(4)
//            conditionRDD_Count = conditionRDD(rule_number).count()
//
////            conditionRDD(rule_number).collect().foreach(println)
//          }
//
//          if (conditionRDD_Count != 0) {
//            val conclusion = rule._2
//            val conclusionRDD = Reasoning(conditionRDD(rule_number), condition, conclusion).repartition(4)
//
//            print("Reasoning Time :")
//            var countConclusion: Long = 0
//            val reasoningTime = time {
//              countConclusion = conclusionRDD.count()
//            inferredCount += countConclusion
//            }
//
////            conclusionRDD.collect().foreach(println)
//            deliberateCount += countConclusion.toInt
////            println("Number of Inferred Triples : " + countConclusion)
////            println("=======================================")
//
//            val conclusionPredicate_idx: Int = Predicate_ID_Finder(predicateMap, conclusion)
//
//            predicateRDD(conclusionPredicate_idx) = predicateRDD(conclusionPredicate_idx).union(conclusionRDD).distinct().repartition(4)
//          }
//          else {
////            println("This rule was not deliberated")
//          }
//          rule_number += 1
//        }
//        println("Inferred Count : "+inferredCount)
//      }
//    }
//  }
//}
