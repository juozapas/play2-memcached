import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play2-memcached"
    val appVersion      = "0.2.1-SNAPSHOT"

  lazy val root = Project("root", base = file("."))
    .dependsOn(plugin)

  lazy val plugin = Project(appName, base = file("plugin")).settings(
      resolvers += "Spy Repository" at "http://files.couchbase.com/maven2",
      libraryDependencies += "spy" % "spymemcached" % "2.6",
      libraryDependencies += "play" %% "play" % "2.1-SNAPSHOT",
      organization := "com.github.mumoshu",
      version := appVersion,
      publishTo <<= version { v: String =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
        else                             Some("releases" at nexus + "service/local/staging/deploy/maven2")
      },
      publishMavenStyle := true,
      publishArtifact in Test := false,
      pomIncludeRepository := { _ => false },
      pomExtra := (
        <url>https://github.com/mumoshu/play2-memcached</url>
          <licenses>
            <license>
              <name>BSD-style</name>
              <url>http://www.opensource.org/licenses/bsd-license.php</url>
              <distribution>repo</distribution>
            </license>
          </licenses>
          <scm>
            <url>git@github.com:mumoshu/play2-memcached.git</url>
            <connection>scm:git:git@github.com:mumoshu/play2-memcached.git</connection>
          </scm>
          <developers>
            <developer>
              <id>you</id>
              <name>KUOKA Yusuke</name>
              <url>https://github.com/mumoshu</url>
            </developer>
          </developers>
        )
    )

   

}
