package org.rvinowise.freeplane.database

import java.io.File


import org.freeplane.plugin.*
import org.freeplane.api.*
//import com.rvinowise.freeplane.*
//import org.rvinowise.freeplane.database.*

class Loader_from_db 
(
	val c: org.freeplane.api.Controller,
	val node: org.freeplane.api.Node,
	val integrator: org.rvinowise.freeplane.database.Database
) {

	var user_dir: File = c.getUserDirectory()
	var sql = integrator.connection

fun load_subtrees(query_node: Node) {
	sql.eachRow(
		"""
		SELECT id FROM node 
		WHERE position(${query_node.text} in text) > 0
		"""
	) { node_db_id ->
		var node = recreate_node_by_db_id(query_node, node_db_id)
		recreate_subtree(node)
	}
}

fun recreate_node_by_db_row(parent_node: Node, node_row) {
	node = parent_node.createChild()
	node.text = node_row.text
	node["db_id"] = node_row.id
	load_icons(node_row.id, node)
	return node
}

fun recreate_subtree(root_node: Node) {
	var root_db_id = root_node["db_id"]
	sql.eachRow(
		"""
		SELECT ending FROM edge 
		WHERE start = '${root_db_id}'
		"""
	) { next_node_db_id ->
		var node = recreate_node_by_db_id(root_node, next_node_db_id)
		recreate_subtree(node)
	}
}

fun recreate_node_by_db_id(parent_node: Node, id: String) {
	sql.firstRow(
		"""
		SELECT text, id FROM node 
		WHERE id = ${id}
		"""
	) { node_row ->
		recreate_node_by_db_row(parent_node, node_row);
	}
}



fun load_icons(node_db_id: String, dst_node: Node) {
	sql.eachRow(
		"""
		SELECT name FROM icon 
		WHERE node = '${node_db_id}'
        ORDER BY position ASC
		"""
	) { icon_name ->
		dst_node.icons.add(icon_name)
	}
}


}