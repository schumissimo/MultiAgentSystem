package core

import java.io.PrintWriter
import java.io.File

abstract class Statistique(file: File) {
	val writer = new PrintWriter(file)

	def this(filename: String) = this(new File(filename))

	def printLine(line: String) {
		writer.print(line)
	}

	def closeWriter {
		writer.close
	}

}