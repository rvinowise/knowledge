import groovy.util.logging.Log
import java.util.logging.FileHandler
import java.util.logging.SimpleFormatter
import groovy.io.FileType
import groovy.time.TimeCategory 
import groovy.time.TimeDuration

import org.apache.commons.io.FilenameUtils 

import com.rvinowise.freeplane.Text_styler
import com.rvinowise.freeplane.Renamer
import com.rvinowise.freeplane.File_system

//test
import java.nio.file.Paths
import java.nio.file.Path
import java.nio.file.Files

Commander com = new Commander(node, c)

com.run_on_node(node)

@Log
class Commander {

def c
def node
def user_dir
def styler //Text_styler
def renamer //Renamer
File_system file_system
def database;


Commander(_node, _c) {
    c = _c
    node = _node
	styler = new Text_styler(c, node)
	renamer = new Renamer(c, node)
	file_system = new File_system(c, node);
	database = new Database_integrator(c,node);

    user_dir = c.getUserDirectory()
    FileHandler handler = new FileHandler("$user_dir/logs/Commander.log", true);
	handler.setFormatter(new SimpleFormatter())
	log.addHandler(handler)
}


def run_on_node(main_node) {

    def commands;
    def text;
    (commands, text) = extract_tags(main_node.text)
    main_node.text = text; 

	c.getSelecteds().each{node -> 
		commands.each{command->
			if (command == "folder"){
				renamer.create_folder_for_branch(node) 
			} else if (command == "name"){
				
			} else if (command == "read"){
				database.read_subtrees(node);
			} else if (command == "write"){
				database.write_subtree(node);
			} else if (styler.is_styling_command(command)) {
				styler.node = node
				styler.apply_command(command)
			} 
		}
	}

}



def extract_tags(String text) {
    def tags = []
    StringBuilder chars = StringBuilder.newInstance()
    chars << text
    def tag_positions = []
    String current_tag = null
    String tag_start = '`'
    for (int i = 0; i < chars.size(); i++){
        char symbol = chars[i]
        char next_symbol = (i<chars.size()-1) ? chars[i+1] : '\0' as char    
        if (symbol == tag_start) {
            if (current_tag != null) {
                tags.add(current_tag)
            }
            chars.setCharAt(i, "\0" as char)
            current_tag = new String()
        } else
        if (symbol == ' ') {
            if (current_tag != null) {
                tags.add(current_tag)
                current_tag = null
                
            }
            if (next_symbol == tag_start) {
                chars.setCharAt(i, "\0" as char)
            }
        } else {
            if (current_tag != null) {
                current_tag = "$current_tag$symbol"
                chars.setCharAt(i, "\0" as char)
            }
        }
    }
    if (current_tag != null) {
        tags.add(current_tag)
    }
    
    String out_text = chars.toString().replaceAll("\0", "").trim()
    
    return [tags, out_text]
}


}

