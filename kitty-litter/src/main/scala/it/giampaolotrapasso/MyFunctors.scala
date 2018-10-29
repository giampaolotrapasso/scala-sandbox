package it.giampaolotrapasso

import cats.Functor

import scala.concurrent.{ExecutionContext, Future}

object MyFunctors {

  implicit def futureFunctor(implicit ec: ExecutionContext): Functor[Future] =
    new Functor[Future] {
      def map[A, B](value: Future[A])(func: A => B): Future[B] =
        value.map(func)
    }

  implicit def treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](tree: Tree[A])(f: A => B): Tree[B] =
      tree match {
        case Leaf(value) => Leaf(f(value))
        case Branch(left, right) => Branch(map(left)(f), map(right)(f))
      }
  }


}

object TreeFunctorSyntax {

  implicit class TreeFunctorOps[A, B](value: Tree[A]) {
    def map(f: A => B)(implicit functor: Functor[Tree]): Tree[B] = functor.map(value)(f)

  }

}
