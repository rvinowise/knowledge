def parent = node.parent;def position = parent.getChildPosition(node)def new_node = parent.createChild()new_node.moveTo(parent, position+1)c.select(new_node)