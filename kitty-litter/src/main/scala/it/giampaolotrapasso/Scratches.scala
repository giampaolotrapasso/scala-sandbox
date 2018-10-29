package it.giampaolotrapasso
import cats.Functor
import it.giampaolotrapasso.MyFunctors._
import it.giampaolotrapasso.TreeFunctorSyntax._
import it.giampaolotrapasso.PrintableSyntax._
import it.giampaolotrapasso.PrintableInstances._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Scratches extends App {

  val future: Future[Int] = Future(133)

  val mappedFuture = Functor[Future].map(future)(_ + 1)

  val mappedValue = Await.result(mappedFuture, 1.second)

  println(mappedValue)

  val l1 = Tree.leaf(5)
  val l2 = Tree.leaf(6)
  val l3 = Tree.leaf(12)
  val tree = Tree.branch(l1, Tree.branch(l2, l3))

  println(tree.map(_ + 1))

  // println("hello".format) ?

  "hello".print



  Printable.print("hello")

  Printable.print(Box("hi"))


















}
