package org.rvinowise.freeplane.database

import org.freeplane.api.Node
import org.postgresql.core.Oid.VARCHAR
import java.io.File
//import java.sql.Types.*
import java.net.URI


class Saver_to_db(
	val c: org.freeplane.api.Controller,
	val node: org.freeplane.api.Node,
	val database: org.rvinowise.freeplane.database.Database
) {

	var user_dir: File = c.getUserDirectory()
	var connection = database.connection
	var file_path: URI = URI.create("")

	fun save_subtree(root_node: Node) {
		file_path = database.find_path_to_file(root_node.mindMap.file)
		val node_db_id = database.get_db_id_for_node(file_path.toString(), root_node)
		update_subtree(node_db_id, root_node)
	}
	fun update_subtree(root_db_id: String, root_node: Node) {
		delete_node(root_db_id)
		add_node(root_db_id, root_node);
		
		root_node.children.forEach {
			val child_db_id = database.get_db_id_for_node(file_path.toString(), it)
			update_subtree(child_db_id, it)
			add_edge_between_nodes(root_db_id, child_db_id)
		}
		c.setStatusInfo("saved node: "+root_db_id)
	}
	fun delete_node(node_db_id: String) {
		delete_node_body(node_db_id)
		delete_icons_for_node(node_db_id)
		delete_edges_starting_with_node(node_db_id)
	}
	fun add_node(node_db_id: String, node: Node) {
		add_node_body(node_db_id, node)
		add_icons_for_node(node_db_id, node)
	}

	fun delete_node_body(node_db_id: String) {
		connection.createStatement().execute("DELETE FROM node WHERE id = ${VARCHAR(node_db_id)}")
	}
	fun add_node_body(node_db_id:String, node:Node) {
		val statement = connection.prepareStatement("INSERT INTO node (text, id) VALUES (?,?)")
		val params = listOf(VARCHAR(node.text), VARCHAR(node_db_id))
		statement.
		val keys = connection.createStatement().executeUpdate(insertSql, params)
		c.statusInfo(keys)
	}


	fun delete_icons_for_node(node_db_id: String) {
		val command = """DELETE FROM icon WHERE node = ${VARCHAR(node_db_id)}"""
		connection.execute(command)
	}
	fun add_icons_for_node(node_db_id:String, node:Node) {
		val insertSql = "INSERT INTO icon (node, name, position) VALUES (?,?,?)"

		val i_icon = 0;
		for (icon_name in node.icons) {
			val params = [
				VARCHAR(node_db_id), 
				VARCHAR(icon_name),
				INTEGER(i_icon)
			]
			connection.executeInsert(insertSql, params)
			i_icon++;
		}
	}

	fun delete_edges_starting_with_node(node_db_id:String) {
		val command = """DELETE FROM edge WHERE start = ${VARCHAR(node_db_id)}"""
		connection.execute(command)
	}

	fun add_edge_between_nodes(parent_db_id:String, child_db_id:String) {
		val insertSql = 'INSERT INTO edge (start, ending) VALUES (?,?)'
		val params = [VARCHAR(parent_db_id), VARCHAR(child_db_id)]
		val keys = connection.executeInsert(insertSql, params)
	}
}