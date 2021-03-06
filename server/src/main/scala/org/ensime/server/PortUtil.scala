// Copyright: 2010 - 2016 https://github.com/ensime/ensime-server/graphs
// Licence: http://www.gnu.org/licenses/gpl-3.0.en.html
package org.ensime.server

import java.io.{ PrintWriter, IOException }
import org.ensime.util.file._

import akka.event.slf4j.SLF4JLogging

object PortUtil extends SLF4JLogging {

  def port(cacheDir: File, name: String): Option[Int] = {
    val portfile = cacheDir / name
    if (portfile.exists()) Some(portfile.readString().toInt)
    else None
  }

  def writePort(cacheDir: File, port: Int, name: String): Unit = {
    val portfile = cacheDir / name
    if (!portfile.exists()) {
      log.info("creating portfile " + portfile)
      portfile.createNewFile()
    }

    portfile.deleteOnExit() // doesn't work on Windows
    portfile.writeString(port.toString)
  }
}
