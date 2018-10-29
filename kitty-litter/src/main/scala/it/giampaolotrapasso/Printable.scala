package it.giampaolotrapasso

trait Printable[A] {
  self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = new Printable[B] {
    def format(value: B): String = self.format(func(value))
  }
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    def format(value: String): String =
      "\"" + value + "\""
  }
  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    def format(value: Boolean): String =
      if (value) "yes" else "no"
  }

  implicit def boxPrintable[A](implicit print: Printable[A]): Printable[Box[A]] = print.contramap(( box :Box[A] ) => box.value)


}

object Printable {
  def format[A](input: A)(implicit p: Printable[A]): String =
    p.format(input)

  def print[A](input: A)(implicit p: Printable[A]): Unit = println(format(input))
}


object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      p.format(value)

    def print(implicit p: Printable[A]): Unit =
      println(p.format(value))
  }

}
