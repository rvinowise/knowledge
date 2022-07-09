package org.rvinowise.freeplane.database

import org.freeplane.api.Node
import org.freeplane.api.Controller

import org.postgresql.ds.PGSimpleDataSource

import java.sql.Connection
import java.net.URI
import java.io.File
import java.nio.file.Paths


class Database(
	val c: Controller,
	val node: Node,
) {

	val user_dir = c.getUserDirectory()
	val connection = create_sql_connection()
	val loader = Loader_from_db(c,node,this)
	val saver = Saver_to_db(c,node,this)

	// init {
	// 	val handler = FileHandler("$user_dir/logs/Database_integrator.log", true)
	// 	handler.setFormatter(new SimpleFormatter())
	// 	log.addHandler(handler)
	// 	log.info("created")
	// }

fun create_sql_connection(): Connection {
	//val db_url = "jdbc:postgresql://localhost:5432/mindmap"
	val source = PGSimpleDataSource()
	source.setServerName("localhost")
	source.setDatabaseName("mindmap")
	source.setUser("postgres")
	source.setPassword(" ")
	var sql = source.getConnection()
	return sql
}

fun save_subtree(root_node: Node) {
	return saver.save_subtree(root_node)
}

fun load_subtrees(query_node: Node) {
	return loader.load_subtrees(query_node)
}


fun get_db_id_for_node(node: Node): String {
	if (node.mindMap.file == null) {
		throw Exception("the file of mind map ${node.map} is null")
	}
	val file_path = find_path_to_file(node.map.file)
	return file_path.toString()+"#"+node.id
}
fun get_db_id_for_node(file_path: String, node: Node):String {
	val existing_id = retrieve_existing_id(node)
	if (existing_id != null) {
		return existing_id
	}
	return create_new_id(file_path, node.id)
}
fun create_new_id(file_path: String, node_id: String): String {
	return file_path+"#"+node_id
}
fun retrieve_existing_id(node: Node): String? {
	val id_attrib = node["db_id"]
	if (id_attrib != null) {
		return id_attrib
	}
	return null
}

fun find_path_to_file(file: File): URI {
	val root_file = find_repository_root_for_file(file)
	val path = when(root_file) {
		null -> Paths.get(file.canonicalPath).toUri()
		else -> get_relative_uri(file, root_file.parentFile.canonicalPath)
	}
	c.setStatusInfo("path = ${path}")
	return path
}

fun find_repository_root_for_file(file: File): File? {
	var parent_file = file.parentFile
	var git_folder: File;
	while (parent_file != null) {
		git_folder = File(parent_file, ".git")
		if (git_folder.exists()) {
			return parent_file;
		}
		parent_file = parent_file.parentFile
	}
	return null
}
fun get_relative_uri(file: File, basedir: String): URI {
	val file_uri = java.nio.file.Paths.get(file.getAbsolutePath()).toUri()
	val base_uri = java.nio.file.Paths.get(basedir).toUri()
	val rel_uri = base_uri.relativize( file_uri )
	return rel_uri

}


}




operator fun Node.get(attrib: String): String? {
	return attributes.getFirst(attrib).toString()
}
operator fun Node.set(attrib: String, value: String) {
	return attributes.set(attrib, value);
}

fun Controller.statusInfo(text: String) {
	this.setStatusInfo(text)
}