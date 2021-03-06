//// @ExecutionModes({ON_SINGLE_NODE})

def position = parent.getChildPosition(node)
def selecteds = c.getSortedSelection(true)
c.statusInfo = selecteds.size()
def new_node = node.parent.createChild()
new_node.moveTo(parent, position)

def final_position = 0
selecteds.each {
    it.moveTo(new_node, final_position)
    final_position++
}

c.select(new_node)
