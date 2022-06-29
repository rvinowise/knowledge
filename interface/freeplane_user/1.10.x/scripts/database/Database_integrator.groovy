import groovy.sql.Sql;
import java.sql.Driver;
import groovy.util.logging.Log
import java.util.logging.FileHandler
import java.util.logging.SimpleFormatter


@Log
class Database_integrator {

def c;
def node;
def user_dir;
def sql;

def Database_integrator(_c, _node) {
	c = _c
	node = _node

	sql = create_sql_connection()

	user_dir = c.getUserDirectory()
    FileHandler handler = new FileHandler("$user_dir/logs/Link_localiser.log", true);
	handler.setFormatter(new SimpleFormatter())
	log.addHandler(handler)
	log.info("created")
}

def create_sql_connection() {
	def driver = Class.forName('org.postgresql.Driver').newInstance() as Driver 

	def props = new Properties()
	props.setProperty("user", "postgres") 
	props.setProperty("password", " ")

	def conn = driver.connect("jdbc:postgresql://localhost:5432/mindmap", props) 
	def sql = new Sql(conn)
	return sql;
}



def write_node(node) {
	def insertSql = 'INSERT INTO node (text, id) VALUES (?,?)'
	def params = [node.text, node.id]
	def keys = sql.executeInsert(insertSql, params)
	c.statusInfo = keys;
}


def write_icons(node) {
	def insertSql = 'INSERT INTO icon (node, icon, position) VALUES (?,?,?)'

	def i_icon = 0;
	for (icon in node.icons) {
		def params = [node.id, icon, i_icon]
		sql.executeInsert(insertSql, params)
		i_icon++;
	}
}

def write_subtree(root_node) {
	write_node(root_node);
	root_node.children.each {
		write_subtree(it)
		write_edge(root_node, it)
	}
}
def read_subtrees(query_node) {
	sql.eachRow(
		"""
		SELECT text, id FROM node 
		WHERE position(${query_node.text} in text) > 0
		"""
	) { node_row ->
		def node = recreate_node(query_node, node_row)
		recreate_subtree(node)
	}
}

def find_db_correspondence_of_node(node) {

}

def recreate_node(parent_node, node_row) {
	node = parent_node.createChild();
	node.text = node_row.text;
	return node;
}

def recreate_subtree(root_node) {
	sql.eachRow(
		"""
		SELECT ending FROM edge 
		WHERE start = '${root_node.id}'
		"""
	) { edge_row ->
		node = recreate_node_by_id(root_node, edge_row.ending)
		recreate_subtree(node)
	}
}

def recreate_node_by_id(parent_node, id) {
	sql.eachRow(
		'''
		SELECT text FROM node 
		WHERE id = ${id}
		'''
	) { node_row ->
		recreate_node(parent_node, node_row);
	}
}



def write_edge(parent, child) {
	def insertSql = 'INSERT INTO edge (start, ending) VALUES (?,?)'
	def params = [parent.id, child.id]
	def keys = sql.executeInsert(insertSql, params)
}

def provide_node(id) {

}

}