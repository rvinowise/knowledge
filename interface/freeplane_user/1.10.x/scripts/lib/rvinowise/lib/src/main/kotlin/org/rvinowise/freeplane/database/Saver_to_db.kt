package org.rvinowise.freeplane.database

import java.sql.Driver;
import java.util.logging.FileHandler
import java.util.logging.SimpleFormatter

class Saver_to_db(
	val c: org.freeplane.api.Controller,
	val node: org.freeplane.api.Node,
	val integrator: org.rvinowise.freeplane.database.Database_integrator
) {

	var user_dir: String = c.getUserDirectory()
	var sql = integrator.sql

	fun save_subtree(root_node) {
		file_path = integrator.find_path_to_file(root_node.map.file)
		val node_db_id = integrator.get_db_id_for_node(file_path, root_node)
		update_subtree(node_db_id, root_node)
	}
	fun update_subtree(root_db_id, root_node) {
		delete_node(root_db_id)
		add_node(root_db_id, root_node);
		
		root_node.children.each {
			child_db_id = integrator.get_db_id_for_node(file_path, it)
			update_subtree(child_db_id, it)
			add_edge_between_nodes(root_db_id, child_db_id)
		}
		c.statusInfo = "saved node: "+root_db_id
	}
	fun delete_node(node_db_id) {
		delete_node_body(node_db_id)
		delete_icons_for_node(node_db_id)
		delete_edges_starting_with_node(node_db_id)
	}
	fun add_node(node_db_id, node) {
		add_node_body(node_db_id, node)
		add_icons_for_node(node_db_id, node)
	}

	fun delete_node_body(node_db_id) {
		val command = """DELETE FROM node WHERE id = ${VARCHAR(node_db_id)}"""
		sql.execute(command)
	}
	fun add_node_body(node_db_id, node) {
		val insertSql = 'INSERT INTO node (text, id) VALUES (?,?)'
		val params = [VARCHAR(node.text), VARCHAR(node_db_id)]
		val keys = sql.executeInsert(insertSql, params)
		c.statusInfo = keys;
	}


	fun delete_icons_for_node(node_db_id) {
		val command = """DELETE FROM icon WHERE node = ${VARCHAR(node_db_id)}"""
		sql.execute(command)
	}
	fun add_icons_for_node(node_db_id, node) {
		val insertSql = 'INSERT INTO icon (node, name, position) VALUES (?,?,?)'

		val i_icon = 0;
		for (icon_name in node.icons) {
			val params = [
				VARCHAR(node_db_id), 
				VARCHAR(icon_name),
				INTEGER(i_icon)
			]
			sql.executeInsert(insertSql, params)
			i_icon++;
		}
	}

	fun delete_edges_starting_with_node(node_db_id) {
		val command = """DELETE FROM edge WHERE start = ${VARCHAR(node_db_id)}"""
		sql.execute(command)
	}

	fun add_edge_between_nodes(parent_db_id, child_db_id) {
		val insertSql = 'INSERT INTO edge (start, ending) VALUES (?,?)'
		val params = [VARCHAR(parent_db_id), VARCHAR(child_db_id)]
		val keys = sql.executeInsert(insertSql, params)
	}
}