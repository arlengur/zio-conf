lazy val root = (project in file("."))
  .settings(
    name         := "zio-conf",
    scalaVersion := "2.13.15",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"                 % "2.1.4",
      "dev.zio" %% "zio-config"          % "4.0.2",
      "dev.zio" %% "zio-config-typesafe" % "4.0.2",
      "dev.zio" %% "zio-config-magnolia" % "4.0.2",
    ),
  )